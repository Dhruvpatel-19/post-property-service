package com.example.postpropertyservice.repository;


import com.example.postpropertyservice.entity.FlatAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatAmenitiesRepository extends JpaRepository<FlatAmenities, Integer> {
    boolean existsByName(String name);

    FlatAmenities findByName(String name);
}
