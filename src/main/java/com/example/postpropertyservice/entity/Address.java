package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @Column(nullable = false)
    private String streetLine;

    private String additionalStreet;

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private int postCode;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Address() {

    }

    public Address(int addressId, String streetLine, String additionalStreet, String city, String state, int postCode, LocalDateTime createdAt) {
        this.addressId = addressId;
        this.streetLine = streetLine;
        this.additionalStreet = additionalStreet;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.createdAt = createdAt;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetLine() {
        return streetLine;
    }

    public void setStreetLine(String streetLine) {
        this.streetLine = streetLine;
    }

    public String getAdditionalStreet() {
        return additionalStreet;
    }

    public void setAdditionalStreet(String additionalStreet) {
        this.additionalStreet = additionalStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
