package com.example.postpropertyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(targetEntity = Property.class)
    @JoinColumn(name = "user_id_fk" , referencedColumnName = "userId")
    private List<Property> propertyList;

    @OneToOne(targetEntity = Favourites.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "favourite_id_fk" , referencedColumnName = "favId")
    private Favourites favourites;

}
