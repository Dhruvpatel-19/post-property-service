package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.Owner;
import com.example.postpropertyservice.exception.OwnerNotFoundException;
import com.example.postpropertyservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner saveOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public List<Owner> findAllOwner(){
        return ownerRepository.findAll();
    }

    public Owner updateOwner(int id , Owner updatedOwner){
        Owner owner = ownerRepository.findById(id).orElse(null);
        if(owner==null)
            throw new OwnerNotFoundException();

        owner.setFirstName(updatedOwner.getFirstName());
        owner.setLastName(updatedOwner.getLastName());
        owner.setMobileNumber(updatedOwner.getMobileNumber());

        return ownerRepository.save(owner);
    }

    public void deleteOwner(int id){
        Owner owner = ownerRepository.findById(id).orElse(null);
        if(owner==null)
            throw new OwnerNotFoundException();

        ownerRepository.deleteById(owner.getOwnerId());
    }
}