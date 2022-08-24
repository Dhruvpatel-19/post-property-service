package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyId;

    @Column(nullable = false)
    private String propertyName;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private int action;

    @Column(nullable = false)
    private int ageYears;

    @Column(nullable = false)
    private String furnishing;
    @Column(nullable = false)
    private Date availableFrom;

    private Date availableTo;

    @Column(nullable = false)
    private String parkingAvailability;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(targetEntity = Image.class , cascade = CascadeType.ALL)
    @JoinColumn(name="property_id_fk" , referencedColumnName = "propertyId" , nullable = false)
    private List<Image> images;

    @ManyToMany(targetEntity = SocietyAmenities.class)
    @JoinTable(name = "property_society_amenities" , joinColumns = @JoinColumn(name = "property_id")  , inverseJoinColumns = @JoinColumn(name = "society_amenities_id") )
    private List<SocietyAmenities> societyAmenities;

    @ManyToMany( targetEntity = FlatAmenities.class)
    @JoinTable( name = "property_flat_amenities" , joinColumns = @JoinColumn(name = "property_id") , inverseJoinColumns = @JoinColumn(name="flat_amenities_id"))
    private List<FlatAmenities> flatAmenities;

    @OneToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id_fk" , referencedColumnName = "categoryId" , nullable = false)
    private Category category;

    @OneToOne(targetEntity = Type.class)
    @JoinColumn(name = "type_id_fk" , referencedColumnName = "typeId" , nullable = false)
    private Type type;

    @OneToOne(targetEntity = Address.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id_fk" , referencedColumnName = "addressId" , nullable = false)
    private Address address;

    public Property() {
    }

    public Property(int propertyId, String propertyName, String price, String area, int action, int ageYears, String furnishing, Date availableFrom, Date availableTo, String parkingAvailability, LocalDateTime createdAt, List<Image> images, List<SocietyAmenities> societyAmenities, List<FlatAmenities> flatAmenities, Category category, Type type, Address address) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.price = price;
        this.area = area;
        this.action = action;
        this.ageYears = ageYears;
        this.furnishing = furnishing;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.parkingAvailability = parkingAvailability;
        this.createdAt = createdAt;
        this.images = images;
        this.societyAmenities = societyAmenities;
        this.flatAmenities = flatAmenities;
        this.category = category;
        this.type = type;
        this.address = address;
    }


    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
