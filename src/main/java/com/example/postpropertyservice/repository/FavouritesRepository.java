package com.example.postpropertyservice.repository;

import com.example.postpropertyservice.entity.Favourites;
import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
    void deleteByProperty(Property property);
}