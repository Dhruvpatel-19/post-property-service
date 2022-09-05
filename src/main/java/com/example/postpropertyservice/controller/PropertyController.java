package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.entity.Property;
import com.example.postpropertyservice.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property added successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "404",description = "Property not found"),
            @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @PostMapping(value = "/addProperty")
    public Property addProperty(HttpServletRequest request, @RequestBody Property property){
         return propertyService.addProperty(request , property);
    }

    @Operation(summary = "Get All Property Listed By Particular Owner",description = "All the property listed by particular owner will be listed for owner", tags = {"PropertyController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Property found successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "404",description = "Property not found"),
            @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @GetMapping(value = "/getAllProperty")
    public List<Property> getAllProperty(HttpServletRequest request){
        return propertyService.getAllProperty(request);
    }

    @Operation(summary = "Get All Unsold Property Listed By Particular Owner",description = "All the remaining unsold property listed by particular owner will be listed for owner", tags = {"PropertyController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Unsold Property found successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "404",description = "Property not found"),
            @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @GetMapping(value = "/getAllProperty/unsold")
    public List<Property> getAllUnsoldProperty(HttpServletRequest request){
        return propertyService.getAllUnsoldProperty(request);
    }

    @Operation(summary = "Update A Given Property",description = "Update a property by the owner who has listed it for selling", tags = {"PropertyController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property found successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "404",description = "Property not found"),
            @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @PutMapping(value = "/updateProperty/{id}")
    public Property updateProperty(HttpServletRequest request, @PathVariable("id") int id , @RequestBody Property updatedProperty){
        return propertyService.updateProperty(request , id , updatedProperty);
    }

    @Operation(summary = "Delete A Given Property",description = "Delete a property by the owner who has listed it for selling", tags = {"PropertyController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "404",description = "Property not found"),
            @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @DeleteMapping(value = "/deleteProperty/{id}")
    public Property deleteProperty(HttpServletRequest request, @PathVariable("id") int id){
        return propertyService.deleteProperty(request, id);
    }


}
