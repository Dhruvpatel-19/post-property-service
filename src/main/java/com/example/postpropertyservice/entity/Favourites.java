package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(targetEntity = Property.class , cascade = CascadeType.ALL)
    @JoinTable( name = "favourite_property" , joinColumns = { @JoinColumn(name = "favId") } ,  inverseJoinColumns = { @JoinColumn(name = "propertyId") } )
    private List<Property> properties;

    public Favourites() {
    }

    public Favourites(int favId, LocalDateTime createdAt, List<Property> properties) {
        this.favId = favId;
        this.createdAt = createdAt;
        this.properties = properties;
    }

    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
        this.favId = favId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
