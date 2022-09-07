package com.example.postpropertyservice.mapstruct;

import com.example.postpropertyservice.dto.AllPropertyDTO;
import com.example.postpropertyservice.dto.PropertyDTO;
import com.example.postpropertyservice.dto.UserDTO;
import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    PropertyDTO propertyToPropertyDto(Property property);

    AllPropertyDTO propertyToAllPropertyDto(Property property);

    UserDTO userToUserDto(User user);
}
