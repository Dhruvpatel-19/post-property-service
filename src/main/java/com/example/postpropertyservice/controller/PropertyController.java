package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.dto.AllPropertyDTO;
import com.example.postpropertyservice.dto.PropertyDTO;
import com.example.postpropertyservice.dto.UserDTO;
import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/postService/")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Add Property For Selling",description = "This property will be open to sell", tags = {"PropertyController"})
    @PostMapping(value = "/addProperty")
    public PropertyDTO addProperty(HttpServletRequest request, @RequestBody Property property){
         return propertyService.addProperty(request , property);
    }
    @Operation(summary = "Get Property",description = "Get property with specific id", tags = {"PropertyController"})
    @GetMapping(value = "/getProperty/{propertyId}")
    public PropertyDTO getProperty(@PathVariable("propertyId") int id){
        return propertyService.getProperty(id);
    }

    @Operation(summary = "Get All Property",description = "All available property will be shown", tags = {"PropertyController"})
    @GetMapping(value = "/getAllProperty")
    public List<AllPropertyDTO> getAllProperty(){
        return propertyService.getAllProperty();
    }


    @Operation(summary = "Get All Property Listed By Particular Owner",description = "All the property listed by particular owner will be listed for owner", tags = {"PropertyController"})
    @GetMapping(value = "/profile/getAllProperty")
    public List<AllPropertyDTO> getAllOwnedProperty(HttpServletRequest request){
        return propertyService.getAllOwnedProperty(request);
    }

    @Operation(summary = "Get All Unsold Property Listed By Particular Owner",description = "All the remaining unsold property listed by particular owner will be listed for owner", tags = {"PropertyController"})
    @GetMapping(value = "/profile/getAllProperty/unsold")
    public List<AllPropertyDTO> getAllOwnedUnsoldProperty(HttpServletRequest request){
        return propertyService.getAllOwnedUnsoldProperty(request);
    }

    @Operation(summary = "Update A Given Property",description = "Update a property by the owner who has listed it for selling", tags = {"PropertyController"})
    @PutMapping(value = "/updateProperty/{id}")
    public PropertyDTO updateProperty(HttpServletRequest request, @PathVariable("id") int id , @RequestBody Property updatedProperty){
        return propertyService.updateProperty(request , id , updatedProperty);
    }

    @Operation(summary = "Delete A Given Property",description = "Delete a property by the owner who has listed it for selling", tags = {"PropertyController"})
    @DeleteMapping(value = "/deleteProperty/{id}")
    public PropertyDTO deleteProperty(HttpServletRequest request, @PathVariable("id") int id){
        return propertyService.deleteProperty(request, id);
    }

    @Operation(summary = "Get A All Request For Property",description = "Users who hase made a request to buy property  will be displayed", tags = {"PropertyController"})
    @GetMapping(value = "/getPropertyReq/{propertyId}")
    public List<UserDTO> getAllReqOfProperty(HttpServletRequest request , @PathVariable("propertyId") int id){
        return propertyService.getAllReqOfProperty(request,id);
    }

    @Operation(summary = "Sell Property To User",description = "Sell a property to specific user who has requested to buy property", tags = {"PropertyController"})
    @GetMapping(value = "/getProperty/{propertyId}/sellProperty/{userId}")
    public PropertyDTO sellPropertyToUser(HttpServletRequest request , @PathVariable("propertyId") int propertyId , @PathVariable("userId") int userId){
        return propertyService.sellPropertyToUser(request , propertyId , userId);
    }


}
