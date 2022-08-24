package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.entity.SocietyAmenities;
import com.example.postpropertyservice.service.SocietyAmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postService/")
public class SocietyAmenitiesController {

    @Autowired
    private SocietyAmenitiesService societyAmenitiesService;

    @PostMapping(value = "/addSocietyAmenities")
    public SocietyAmenities addSocietyAmenities(@RequestBody SocietyAmenities societyAmenities){
        return societyAmenitiesService.addSocietyAmenities(societyAmenities);
    }

    @GetMapping(value = "/getSocietyAmenitiesById/{id}")
    public SocietyAmenities getSocietyAmenities(@PathVariable("id") int id){
        return societyAmenitiesService.getSocietyAmenities(id);
    }

    @GetMapping(value = "/getAllSocietyAmenities")
    public List<SocietyAmenities> getAllSocietyAmenities(){
        return societyAmenitiesService.getAllSocietyAmenities();
    }

    @PutMapping(value = "/updateSocietyAmenities/{id}")
    public SocietyAmenities updateSocietyAmenities(@PathVariable("id") int id , @RequestBody SocietyAmenities societyAmenities){
        return societyAmenitiesService.updateSocietyAmenities(id , societyAmenities);
    }

    @DeleteMapping(value = "/deleteSocietyAmenities/{id}")
    public String deleteSocietyAmenities(@PathVariable("id") int id){
        return societyAmenitiesService.deleteSocietyAmenities(id);
    }
}
