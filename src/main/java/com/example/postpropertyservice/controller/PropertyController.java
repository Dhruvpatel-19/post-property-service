package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/postService/")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping(value = "/addProperty")
    public String addProperty(HttpServletRequest request, @RequestBody Property property) throws Exception {
         return propertyService.addProperty(request , property);
    }

    @PutMapping(value = "/updateProperty/{id}")
    public String updateProperty(HttpServletRequest request, @PathVariable("id") int id , @RequestBody Property updatedProperty) throws Exception {
       //return propertyService.updateProperty(request , id , updatedProperty);
        return propertyService.updateProperty(request , id , updatedProperty);
    }

    @DeleteMapping(value = "/deleteProperty/{id}")
    public String deleteProperty(HttpServletRequest request, @PathVariable("id") int id) throws Exception {
        return propertyService.deleteProperty(request, id);
    }


}