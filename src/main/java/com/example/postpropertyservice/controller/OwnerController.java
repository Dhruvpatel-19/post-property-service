package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.entity.Owner;
import com.example.postpropertyservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/callPostService/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/register")
    public Owner saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }

    @GetMapping(value = "/findAll")
    public List<Owner> findAllOwner(){
        return ownerService.findAllOwner();
    }

    @PutMapping(value = "/update/{id}")
    public void updateOwner(@PathVariable("id") int id ,@RequestBody Owner updatedOwner){
        ownerService.updateOwner(id , updatedOwner);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteOwner(@PathVariable("id") int id){
        ownerService.deleteOwner(id);
    }
}
