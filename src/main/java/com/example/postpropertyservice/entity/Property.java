package com.example.postpropertyservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private boolean isSold;

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

    @ManyToOne(targetEntity = Owner.class)
    @JoinColumn(name="owner_id_fk",referencedColumnName = "ownerId")
    @JsonIgnore
    private Owner owner;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id_fk" , referencedColumnName = "userId")
    @JsonIgnore
    private User user;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "property_requser" , joinColumns = @JoinColumn(name = "property_id") , inverseJoinColumns = @JoinColumn(name="user_id"))
    @JsonIgnore
    private Set<User> reqUsers;
}
