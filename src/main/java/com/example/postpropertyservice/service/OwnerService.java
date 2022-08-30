package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.Owner;
import com.example.postpropertyservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner saveOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public List<Owner> findAllOwner(){
        return ownerRepository.findAll();
    }

    public void updateOwner(int id , Owner updatedOwner){
        Owner owner = ownerRepository.findById(id).orElse(null);

        owner.setFirstName(updatedOwner.getFirstName());
        owner.setLastName(updatedOwner.getLastName());
        owner.setMobileNumber(updatedOwner.getMobileNumber());

        ownerRepository.save(owner);
    }
}