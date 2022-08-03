package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyId;

    @Column(nullable = false)
    private int action;

    @Column(nullable = false)
    private int ageYears;
    @Column(nullable = false)
    private String furnishing;
    @Column(nullable = false)
    private String availableFrom;
    @Column(nullable = false)
    private String availableTo;
    @Column(nullable = false)
    private String parkingAvailability;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(targetEntity = SocietyAmenities.class , cascade = CascadeType.ALL)
    @JoinColumn(name="property_id_fk",referencedColumnName = "propertyId" , nullable = false)
    private List<SocietyAmenities> societyAmenities;

    @OneToMany(targetEntity = FlatAmenities.class , cascade = CascadeType.ALL)
    @JoinColumn(name="property_id_fk",referencedColumnName = "propertyId" , nullable = false)
    private List<FlatAmenities> flatAmenities;

    @OneToOne(targetEntity = Category.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id_fk" , referencedColumnName = "categoryId" , nullable = false)
    private Category category;

    @OneToOne(targetEntity = Type.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id_fk" , referencedColumnName = "typeId" , nullable = false)
    private Type type;

    @OneToOne(targetEntity = Address.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id_fk" , referencedColumnName = "addressId" , nullable = false)
    private Address address;




    public Property() {
    }

    public Property(int propertyId, int action, int ageYears, String furnishing, String availableFrom, String availableTo, String parkingAvailability, LocalDateTime createdAt, List<SocietyAmenities> societyAmenities, List<FlatAmenities> flatAmenities, Category category) {
        this.propertyId = propertyId;
        this.action = action;
        this.ageYears = ageYears;
        this.furnishing = furnishing;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.parkingAvailability = parkingAvailability;
        this.createdAt = createdAt;
        this.societyAmenities = societyAmenities;
        this.flatAmenities = flatAmenities;
        this.category = category;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
    }

    public String getFurnishing() {
        return furnishing;
    }

    public void setFurnishing(String furnishing) {
        this.furnishing = furnishing;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    public String getParkingAvailability() {
        return parkingAvailability;
    }

    public void setParkingAvailability(String parkingAvailability) {
        this.parkingAvailability = parkingAvailability;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<SocietyAmenities> getSocietyAmenities() {
        return societyAmenities;
    }

    public void setSocietyAmenities(List<SocietyAmenities> societyAmenities) {
        this.societyAmenities = societyAmenities;
    }

    public List<FlatAmenities> getFlatAmenities() {
        return flatAmenities;
    }

    public void setFlatAmenities(List<FlatAmenities> flatAmenities) {
        this.flatAmenities = flatAmenities;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
