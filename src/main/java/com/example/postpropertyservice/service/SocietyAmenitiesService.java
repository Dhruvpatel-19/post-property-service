package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.SocietyAmenities;
import com.example.postpropertyservice.repository.SocietyAmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SocietyAmenitiesService {

    @Autowired
    private SocietyAmenitiesRepository societyAmenitiesRepository;

    @Autowired
    private RestTemplate restTemplate;

    public SocietyAmenities addSocietyAmenities(SocietyAmenities societyAmenities){
        HttpEntity<SocietyAmenities> societyAmenitiesObj = new HttpEntity<>(societyAmenities);
        restTemplate.postForEntity("http://localhost:8081/callGuestService/addSocietyAmenities" , societyAmenitiesObj , String.class);
        return societyAmenitiesRepository.save(societyAmenities);
    }

    public SocietyAmenities getSocietyAmenities(int id){
        return societyAmenitiesRepository.findById(id).orElse(null);
    }

    public List<SocietyAmenities> getAllSocietyAmenities(){
        return societyAmenitiesRepository.findAll();
    }

    public SocietyAmenities updateSocietyAmenities(int id , SocietyAmenities updateSocietyAmenities){
        if(!societyAmenitiesRepository.existsById(id))
            return null;
        SocietyAmenities societyAmenities = societyAmenitiesRepository.findById(id).orElse(null);

        societyAmenities.setName(updateSocietyAmenities.getName());

        HttpEntity<SocietyAmenities> societyAmenitiesObj = new HttpEntity<>(societyAmenities);
        restTemplate.exchange("http://localhost:8081/callGuestService/updateSocietyAmenities/"+id , HttpMethod.PUT , societyAmenitiesObj , String.class);

        societyAmenitiesRepository.save(societyAmenities);

        return societyAmenities;
    }

    public String deleteSocietyAmenities(int id){
        if(!societyAmenitiesRepository.existsById(id))
            return "SocietyAmenities with id "+id+" doesn't exists" ;

        SocietyAmenities societyAmenities = societyAmenitiesRepository.findById(id).orElse(null);

        restTemplate.delete("http://localhost:8081/callGuestService/deleteSocietyAmenities/"+id);
        societyAmenitiesRepository.deleteById(id);

        return "SocietyAmenities with name "+societyAmenities.getName()+" deleted successfully";
    }
}
