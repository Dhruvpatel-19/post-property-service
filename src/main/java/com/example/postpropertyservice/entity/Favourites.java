package com.example.postpropertyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(targetEntity = Property.class)
    @JoinTable(name = "favId_propertyId" , joinColumns = @JoinColumn(name = "favId") , inverseJoinColumns = @JoinColumn(name = "propertyId") )
    private Set<Property> propertyList;
}
