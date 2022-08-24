package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class FlatAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flatAmenitiesId;

    @Column(nullable = false)
    private String name;

    public FlatAmenities() {
    }

    public FlatAmenities(int flatAmenitiesId, String name) {
        this.flatAmenitiesId = flatAmenitiesId;
        this.name = name;
    }

    public int getFlatAmenitiesId() {
        return flatAmenitiesId;
    }

    public void setFlatAmenitiesId(int flatAmenitiesId) {
        this.flatAmenitiesId = flatAmenitiesId;
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
        FlatAmenities that = (FlatAmenities) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}