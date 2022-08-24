package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.Type;
import com.example.postpropertyservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type addType(Type type){
        return typeRepository.save(type);
    }

    public Type getType(int id){
        return typeRepository.findById(id).orElse(null);
    }

    public List<Type> getAllType(){
        return typeRepository.findAll();
    }

    public Type updateType(int id , Type updatedType){
        if(!typeRepository.existsById(id))
            return null;

        Type type = typeRepository.findById(id).orElse(null);

        type.setType(updatedType.getType());

        return typeRepository.save(type);
    }

    public String deleteType(int id){
        boolean isExists = typeRepository.existsById(id);

        if(isExists){
            Type type = typeRepository.findById(id).orElse(null);

            typeRepository.deleteById(id);

            return "Type with name "+type.getType()+" deleted successfully";
        }else{
            return "Type with id "+id+" doesn't exist";
        }
    }
}
