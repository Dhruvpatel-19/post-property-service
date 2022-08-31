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

    /*@Column(nullable = false)
    private LocalDateTime createdAt;*/

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id_fk" , referencedColumnName = "userId")
    private User user;

    @ManyToOne(targetEntity = Property.class)
    @JoinColumn(name = "property_id_fk" , referencedColumnName = "propertyId")
    private Property property;
}
