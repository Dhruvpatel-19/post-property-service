package com.example.postpropertyservice.service;

import com.example.postpropertyservice.entity.Type;
import com.example.postpropertyservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Type addType(Type type){
        HttpEntity<Type> typeObj = new HttpEntity<>(type);
        restTemplate.postForEntity("http://localhost:8081/callGuestService/addType" , typeObj , String.class );
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

        HttpEntity<Type> typeObj = new HttpEntity<>(type);
        restTemplate.exchange("http://localhost:8081/callGuestService/updateType/"+id , HttpMethod.PUT ,typeObj, String.class);
        return typeRepository.save(type);
    }

    public String deleteType(int id){
        boolean isExists = typeRepository.existsById(id);

        if(isExists){
            Type type = typeRepository.findById(id).orElse(null);

            restTemplate.delete("http://localhost:8081/callGuestService/deleteType/"+id);
            typeRepository.deleteById(id);

            return "Type with name "+type.getType()+" deleted successfully";
        }else{
            return "Type with id "+id+" doesn't exist";
        }
    }
}
