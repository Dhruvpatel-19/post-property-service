package com.example.postpropertyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReqProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userReqPropId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id_fk" , referencedColumnName = "userId")
    private User user;

    @ManyToOne(targetEntity = Property.class)
    @JoinColumn(name = "property_id_fk" , referencedColumnName = "propertyId")
    private Property property;

}
