package com.example.postpropertyservice.repository;

import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.entity.User;
import com.example.postpropertyservice.entity.UserReqProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReqPropertyRepository extends JpaRepository<UserReqProperty, Integer> {

    List<UserReqProperty> findByProperty(Property property);

    boolean existsByUserAndProperty(User user, Property property);
}