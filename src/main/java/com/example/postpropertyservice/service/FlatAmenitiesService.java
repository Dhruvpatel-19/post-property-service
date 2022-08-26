package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.FlatAmenities;
import com.example.postpropertyservice.repository.FlatAmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlatAmenitiesService {

    @Autowired
    private FlatAmenitiesRepository flatAmenitiesRepository;

    @Autowired
    private RestTemplate restTemplate;

    public FlatAmenities addFlatAmenities(FlatAmenities flatAmenities){
        HttpEntity<FlatAmenities> flatAmenitiesObj = new HttpEntity<>(flatAmenities);
        restTemplate.postForEntity("http://localhost:8081/callGuestService/addFlatAmenities" , flatAmenitiesObj , String.class);
        return flatAmenitiesRepository.save(flatAmenities);
    }

    public FlatAmenities getFlatAmenities(int id){
        return flatAmenitiesRepository.findById(id).orElse(null);
    }

    public List<FlatAmenities> getAllFlatAmenities(){
        return flatAmenitiesRepository.findAll();
    }

    public FlatAmenities updateFlatAmenities(int id , FlatAmenities updatedFlatAmenities){
        if(!flatAmenitiesRepository.existsById(id))
            return null;

        FlatAmenities flatAmenities = flatAmenitiesRepository.findById(id).orElse(null);

        flatAmenities.setName(updatedFlatAmenities.getName());

        HttpEntity<FlatAmenities> flatAmenitiesObj = new HttpEntity<>(flatAmenities);
        restTemplate.exchange("http://localhost:8081/callGuestService/updateFlatAmenities/"+id , HttpMethod.PUT , flatAmenitiesObj , String.class);

        flatAmenitiesRepository.save(flatAmenities);

        return flatAmenities;
    }

    public String deleteFlatAmenities(int id){
        if(!flatAmenitiesRepository.existsById(id)){
            return "FlatAmenities with id "+id+" doesn't exist";
        }

        FlatAmenities flatAmenities = flatAmenitiesRepository.findById(id).orElse(null);

        restTemplate.delete("http://localhost:8081/callGuestService/deleteFlatAmenities/"+id);

        flatAmenitiesRepository.deleteById(id);

        return "FlatAmenities with name "+flatAmenities.getName()+" deleted successfully";
    }
}
