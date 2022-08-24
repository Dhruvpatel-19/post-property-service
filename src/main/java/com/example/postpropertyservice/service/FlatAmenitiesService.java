package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.FlatAmenities;
import com.example.postpropertyservice.repository.FlatAmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatAmenitiesService {

    @Autowired
    private FlatAmenitiesRepository flatAmenitiesRepository;

    public FlatAmenities addFlatAmenities(FlatAmenities flatAmenities){
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

        flatAmenitiesRepository.save(flatAmenities);

        return flatAmenities;
    }

    public String deleteFlatAmenities(int id){
        if(!flatAmenitiesRepository.existsById(id)){
            return "FlatAmenities with id "+id+" doesn't exist";
        }

        FlatAmenities flatAmenities = flatAmenitiesRepository.findById(id).orElse(null);

        flatAmenitiesRepository.deleteById(id);

        return "FlatAmenities with name "+flatAmenities.getName()+" deleted successfully";
    }
}
