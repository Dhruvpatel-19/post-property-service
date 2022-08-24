package com.example.postpropertyservice.repository;

import com.example.postpropertyservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByStreetLineAndAdditionalStreetAndCityAndStateAndPostCode(String streetLine, String additionalStreet, String city, String state, int postCode);
}
