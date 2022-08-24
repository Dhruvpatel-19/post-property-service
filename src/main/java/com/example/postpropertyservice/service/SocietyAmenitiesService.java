package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.SocietyAmenities;
import com.example.postpropertyservice.repository.SocietyAmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocietyAmenitiesService {

    @Autowired
    private SocietyAmenitiesRepository societyAmenitiesRepository;

    public SocietyAmenities addSocietyAmenities(SocietyAmenities societyAmenities){
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

        societyAmenitiesRepository.save(societyAmenities);

        return societyAmenities;
    }

    public String deleteSocietyAmenities(int id){
        if(!societyAmenitiesRepository.existsById(id))
            return "SocietyAmenities with id "+id+" doesn't exists" ;

        SocietyAmenities societyAmenities = societyAmenitiesRepository.findById(id).orElse(null);

        societyAmenitiesRepository.deleteById(id);

        return "SocietyAmenities with name "+societyAmenities.getName()+" deleted successfully";
    }
}
