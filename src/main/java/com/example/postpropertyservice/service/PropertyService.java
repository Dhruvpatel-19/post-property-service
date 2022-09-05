package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.*;
import com.example.postpropertyservice.exception.NotAuthenticatedOwner;
import com.example.postpropertyservice.exception.OwnerNotFoundException;
import com.example.postpropertyservice.exception.PropertyNotFoundException;
import com.example.postpropertyservice.jwt.JwtUtil;
import com.example.postpropertyservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private FlatAmenitiesRepository flatAmenitiesRepository;

    @Autowired
    private SocietyAmenitiesRepository societyAmenitiesRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public Property addProperty(HttpServletRequest request, Property property){

        property.setCreatedAt(LocalDateTime.now());
        property.setSold(false);

        Category category = property.getCategory();
        boolean categoryExists = categoryRepository.existsByCategory(category.getCategory());
        if(categoryExists)
            category = categoryRepository.findByCategory(category.getCategory());
        else
            category = categoryRepository.findByCategory("Other");
        property.setCategory(category);


        Type type = property.getType();
        boolean typeExists = typeRepository.existsByType(type.getType());
        if(typeExists)
            type = typeRepository.findByType(type.getType());
        else
            type = typeRepository.findByType("Other");
        property.setType(type);


        List<FlatAmenities> flatAmenitiesList = property.getFlatAmenities();
        List<FlatAmenities> flatAmenitiesListNew = new ArrayList<>();
        FlatAmenities flatAmenities ;
        for(int i=0 ; i<flatAmenitiesList.size() ; i++){
             flatAmenities = flatAmenitiesList.get(i);

            if(flatAmenitiesRepository.existsByName(flatAmenities.getName())){
                flatAmenities = flatAmenitiesRepository.findByName(flatAmenities.getName());
            }
            else{
                flatAmenities = flatAmenitiesRepository.findByName("Other");
            }

            flatAmenitiesListNew.add(flatAmenities);
        }
        property.setFlatAmenities(flatAmenitiesListNew);


        List<SocietyAmenities> societyAmenitiesList = property.getSocietyAmenities();
        List<SocietyAmenities> societyAmenitiesListNew = new ArrayList<>();
        SocietyAmenities societyAmenities;
        for(int i=0 ; i<societyAmenitiesList.size() ; i++){
            societyAmenities = societyAmenitiesList.get(i);

            if(societyAmenitiesRepository.existsByName(societyAmenities.getName())){
                societyAmenities = societyAmenitiesRepository.findByName(societyAmenities.getName());
            }
            else{
                societyAmenities = societyAmenitiesRepository.findByName("Other");
            }

            societyAmenitiesListNew.add(societyAmenities);
        }
        property.setSocietyAmenities(societyAmenitiesListNew);

        Owner owner = (Owner) getOwnerOrUser(request);
        if(owner == null){
            throw new OwnerNotFoundException();
        }
        HttpEntity<Property> propertyObj = new HttpEntity<>(property);
        restTemplate.postForEntity("http://localhost:8081/callGuestService/addProperty" , propertyObj , String.class);

        List<Property> propertyList = owner.getProperties();
        propertyList.add(property);
        owner.setProperties(propertyList);
        ownerRepository.save(owner);

        property.setOwner(owner);
        propertyRepository.save(property);

        return property;
    }

    public Property getProperty(int id){
        return propertyRepository.findById(id).orElse(null);
    }

    public List<Property> getAllProperty(HttpServletRequest request){
        Owner owner = (Owner) getOwnerOrUser(request);
        if(owner!=null){
            return owner.getProperties();
        }
        return null;
    }

    public List<Property> getAllUnsoldProperty(HttpServletRequest request){
        List<Property> propertyList = getAllProperty(request);
        List<Property> unsoldPropertyList = new ArrayList<>();
        for(Property property : propertyList){
            if(!property.isSold())
                unsoldPropertyList.add(property);
        }
        return unsoldPropertyList;
    }

    public Property updateProperty(HttpServletRequest request, int id , Property updatedProperty){

        if(!propertyRepository.existsById(id)){
           throw new PropertyNotFoundException();
        }

        Owner owner = (Owner) getOwnerOrUser(request);

        if(owner == null)
        {
            throw new OwnerNotFoundException();
        }

        Property property = propertyRepository.findById(id).orElse(null);

        if(property==null){
            throw new PropertyNotFoundException();
        }

        if (owner.getEmail().equals(property.getOwner().getEmail()))
            return updateProperty(id , property , updatedProperty);
        else
            throw new NotAuthenticatedOwner();
    }

    private Property updateProperty(int id ,  Property property , Property updatedProperty){
        property.setPrice(updatedProperty.getPrice());
        property.setPropertyName(updatedProperty.getPropertyName());
        property.setArea(updatedProperty.getArea());
        property.setAction(updatedProperty.getAction());
        property.setAgeYears(updatedProperty.getAgeYears());
        property.setFurnishing(updatedProperty.getFurnishing());
        property.setAvailableFrom(updatedProperty.getAvailableFrom());
        property.setAvailableTo(updatedProperty.getAvailableTo());
        property.setParkingAvailability(updatedProperty.getParkingAvailability());
        property.setSold(updatedProperty.isSold());

        if(!compareListsImages(property.getImages() , updatedProperty.getImages())  ) {
            imageRepository.deleteAllInBatch(property.getImages());
            property.setImages(updatedProperty.getImages());
        }


        if(!compareListsSocietyAmenities(property.getSocietyAmenities() , updatedProperty.getSocietyAmenities())) {
            List<SocietyAmenities> societyAmenitiesList = updatedProperty.getSocietyAmenities();
            List<SocietyAmenities> societyAmenitiesListNew = new ArrayList<>();
            SocietyAmenities societyAmenities;
            for (int i = 0; i < societyAmenitiesList.size(); i++) {
                societyAmenities = societyAmenitiesList.get(i);
                if (societyAmenitiesRepository.existsByName(societyAmenities.getName())) {
                    societyAmenities = societyAmenitiesRepository.findByName(societyAmenities.getName());
                } else {
                    societyAmenities = societyAmenitiesRepository.findByName("Other");
                }
                societyAmenitiesListNew.add(societyAmenities);
            }
            property.setSocietyAmenities(societyAmenitiesListNew);
        }


        if(!compareListsFlatAmenities(property.getFlatAmenities() , updatedProperty.getFlatAmenities())) {
            List<FlatAmenities> flatAmenitiesList = updatedProperty.getFlatAmenities();
            List<FlatAmenities> flatAmenitiesListNew = new ArrayList<>();
            FlatAmenities flatAmenities;
            for(int i=0 ; i<flatAmenitiesList.size() ; i++){
                flatAmenities = flatAmenitiesList.get(i);
                if(flatAmenitiesRepository.existsByName(flatAmenities.getName())){
                    flatAmenities = flatAmenitiesRepository.findByName(flatAmenities.getName());
                }else{
                    flatAmenities = flatAmenitiesRepository.findByName("Other");
                }
                flatAmenitiesListNew.add(flatAmenities);
            }
            property.setFlatAmenities(flatAmenitiesListNew);
        }

        if(!property.getCategory().equals(updatedProperty.getCategory())) {
            boolean categoryExists = categoryRepository.existsByCategory(updatedProperty.getCategory().getCategory());
            Category category;
            if (categoryExists) {
                category = categoryRepository.findByCategory(updatedProperty.getCategory().getCategory());
            } else {
                category = categoryRepository.findByCategory("Other");
            }
            property.setCategory(category);
        }

        if(!property.getType().equals(updatedProperty.getType())){
            boolean typeExists = typeRepository.existsByType(updatedProperty.getType().getType());
            Type type;
            if (typeExists) {
                type = typeRepository.findByType(updatedProperty.getType().getType());
            } else {
                type = typeRepository.findByType("Other");
            }
            property.setType(type);
        }


        if(!property.getAddress().equals(updatedProperty.getAddress())) {
            Address updatedAddress = updatedProperty.getAddress();

            boolean addressExists = addressRepository.existsByStreetLineAndAdditionalStreetAndCityAndStateAndPostCode(updatedAddress.getStreetLine(), updatedAddress.getAdditionalStreet(), updatedAddress.getCity(), updatedAddress.getState(), updatedAddress.getPostCode());
            if (!addressExists) {

                Address address = property.getAddress();
                address.setStreetLine(updatedAddress.getStreetLine());
                address.setAdditionalStreet(updatedAddress.getAdditionalStreet());
                address.setCity(updatedAddress.getCity());
                address.setState(updatedAddress.getState());
                address.setState(updatedAddress.getState());
                address.setPostCode(updatedAddress.getPostCode());

                addressRepository.save(address);
                property.setAddress(address);
            }
        }

        HttpEntity<Property> propertyObj = new HttpEntity<>(property);
        //restTemplate.postForEntity("http://localhost:8081/callGuestService/addProperty" , propertyObj , String.class);
        restTemplate.exchange("http://localhost:8081/callGuestService/updateProperty/"+id , HttpMethod.PUT , propertyObj , String.class);

        return propertyRepository.save(property);
    }

    public Property deleteProperty(HttpServletRequest request,int id){

        Property property = propertyRepository.findById(id).orElse(null);

        if(property == null){
            throw new PropertyNotFoundException();
        }

        if(property.isSold()) {
            throw new NotAuthenticatedOwner();
        }

        Owner owner = (Owner) getOwnerOrUser(request);

        if(owner==null) {
            throw new OwnerNotFoundException();
        }

        if(owner.getEmail().equals(property.getOwner().getEmail())){
            favouritesRepository.deleteByProperty(property);
            restTemplate.delete("http://localhost:8081/callGuestService/deleteProperty/"+id);
            propertyRepository.deleteById(id);
            return property;
        }
        else
            throw new NotAuthenticatedOwner();
    }

    private boolean compareListsImages(List<Image> prevList , List<Image> nextList){
        if(prevList.size()!=nextList.size())
            return false;

        for(int i=0 ; i<prevList.size() ; i++){
            if( !prevList.get(i).equals(nextList.get(i)) )
                return false;
        }

        return true;
    }

    private boolean compareListsSocietyAmenities(List<SocietyAmenities> prevList , List<SocietyAmenities> nextList){
        if(prevList.size()!=nextList.size())
            return false;

        for(int i=0 ; i<prevList.size() ; i++){
            if( !prevList.get(i).equals(nextList.get(i)) )
                return false;
        }

        return true;
    }
    private boolean compareListsFlatAmenities(List<FlatAmenities> prevList , List<FlatAmenities> nextList){
        if(prevList.size()!=nextList.size())
            return false;

        for(int i=0 ; i<prevList.size() ; i++){
            if( !prevList.get(i).equals(nextList.get(i)) )
                return false;
        }

        return true;
    }
    private Object getOwnerOrUser(HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);

            email = jwtUtil.extractUsername(jwtToken);

            User user = userRepository.findByEmail(email);
            Owner owner = ownerRepository.findByEmail(email);

            if(user == null && owner==null)
                throw new OwnerNotFoundException();

            if(user!=null)
                return user;
            else
                return owner;
        }
        return null;
    }

}
