package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocietyAmenities that = (SocietyAmenities) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

