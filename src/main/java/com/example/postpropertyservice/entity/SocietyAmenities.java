package com.example.postpropertyservice.entity;

import javax.persistence.*;


@Entity
public class SocietyAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int societyAmenitiesId;
    @Column(nullable = false)
    private String name;

    public SocietyAmenities() {
    }

    public SocietyAmenities(int societyAmenitiesId, String name) {
        this.societyAmenitiesId = societyAmenitiesId;
        this.name = name;
    }

    public int getSocietyAmenitiesId() {
        return societyAmenitiesId;
    }

    public void setSocietyAmenitiesId(int societyAmenitiesId) {
        this.societyAmenitiesId = societyAmenitiesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
