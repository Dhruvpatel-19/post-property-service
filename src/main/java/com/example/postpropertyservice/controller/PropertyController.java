package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postService/")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping(value = "/addProperty")
    public Property addProperty(@RequestBody Property property){
        return propertyService.addProperty(property);
    }

    @PutMapping(value = "/updateProperty/{id}")
    public Property updateProperty(@PathVariable("id") int id , @RequestBody Property updatedProperty){
       return propertyService.updateProperty(id , updatedProperty);
    }

    @DeleteMapping(value = "/deleteProperty/{id}")
    public String deleteProperty(@PathVariable("id") int id){
        return propertyService.deleteProperty(id);
    }


}
