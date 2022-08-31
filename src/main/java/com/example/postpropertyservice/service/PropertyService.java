package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.*;
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
import java.util.Set;

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

    public String addProperty(HttpServletRequest request, Property property) throws Exception {

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

        String requestHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
            jwtToken = requestHeader.substring(7);

            try{
                email = jwtUtil.extractUsername(jwtToken);
            }catch (Exception e){
                throw new Exception("Owner not found exception");
            }

            HttpEntity<Property> propertyObj = new HttpEntity<>(property);
            restTemplate.postForEntity("http://localhost:8081/callGuestService/addProperty" , propertyObj , String.class);
            propertyRepository.save(property);

            Owner owner = ownerRepository.findByEmail(email);
            List<Property> propertyList = owner.getProperties();
            propertyList.add(property);
            owner.setProperties(propertyList);
            ownerRepository.save(owner);
            return "Property added successfully";
        }

        return "Some error occured for addProperty ";
    }

    public Property getProperty(int id){
        return propertyRepository.findById(id).orElse(null);
    }

    public List<Property> getAllProperty(HttpServletRequest request) throws Exception {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtUtil.extractUsername(jwtToken);
            }catch (Exception e){
                throw new Exception("User not found");
            }
            Owner owner = ownerRepository.findByEmail(email);

            return owner.getProperties();
        }

        return null;
    }

    public List<Property> getAllUnsoldProperty(HttpServletRequest request) throws Exception {
        List<Property> propertyList = getAllProperty(request);
        List<Property> unsoldPropertyList = null;
        for(Property property : propertyList){
            if(property.isSold() == false)
                unsoldPropertyList.add(property);
        }
        return unsoldPropertyList;
    }

    public String updateProperty(HttpServletRequest request, int id , Property updatedProperty) throws Exception {

        boolean propertyExists = propertyRepository.existsById(id);
        if(!propertyExists){
            return "Property doesn't exists";
        }

        Property property = propertyRepository.findById(id).orElse(null);

        String requestHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;
        if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
            jwtToken = requestHeader.substring(7);
            try{
                email = jwtUtil.extractUsername(jwtToken);
            }catch (Exception e){
                throw new Exception("Owner not found exception");
            }
            Owner owner = ownerRepository.findByEmail(email);
            List<Property> propertyList = owner.getProperties();
            boolean isOwned = false;

            for(int i=0 ; i<propertyList.size() ; i++){
                if(propertyList.get(i) == property){
                    isOwned = true;
                    break;
                }
            }

            if (isOwned)
                return updateProperty(id , property , updatedProperty);
            else
                return "Property is owned by another owner";
        }
        else {
            return "Some errror occured for updateProperty";
        }
    }

    private String updateProperty(int id ,  Property property , Property updatedProperty){
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
            boolean catagoryExists = categoryRepository.existsByCategory(updatedProperty.getCategory().getCategory());
            Category category;
            if (catagoryExists) {
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

        propertyRepository.save(property);
        return "Property updated successfully";
    }

    public String deleteProperty(HttpServletRequest request,int id) throws Exception {
        boolean isExist = propertyRepository.existsById(id);

        if(!isExist)
            return "Property doesn't exist";

        Property property = propertyRepository.findById(id).orElse(null);

        String requestHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
            jwtToken = requestHeader.substring(7);
            try{
                email = jwtUtil.extractUsername(jwtToken);
            }catch (Exception e){
                throw new Exception("Owner not found exception");
            }
            Owner owner = ownerRepository.findByEmail(email);
            List<Property> propertyList = owner.getProperties();
            boolean isOwned = false;

            for(int i=0 ; i<propertyList.size() ; i++){
                if(propertyList.get(i) == property)
                {
                    isOwned = true;
                    break;
                }
            }

            if(isOwned){
                favouritesRepository.deleteByProperty(property);
                restTemplate.delete("http://localhost:8081/callGuestService/deleteProperty/"+id);
                propertyRepository.deleteById(id);
                return "Property with name " +property.getPropertyName()+" deleted successfully";
            }
            else
                return "Property is owned by another Owner";

        }
        else
            return "Some error occured for deleteProperty";
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

}
