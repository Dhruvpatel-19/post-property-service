package com.example.postpropertyservice.repository;

import com.example.postpropertyservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner , Integer> {
    Owner findByEmail(String email);
}
