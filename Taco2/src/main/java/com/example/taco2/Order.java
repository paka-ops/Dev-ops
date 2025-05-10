package com.example.taco2;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "taco_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is requierd")
    private String deliveryName;
    @NotBlank(message = "Street is requierd")
    private String deliveryStreet;
    @NotBlank(message = "City is requierd")
    private String deliveryCity;
    @NotBlank(message = "State is requierd")
    private String deliveryState;
    @NotBlank(message = "Zip is requierd")
    private String deliveryZip;
    @NotBlank(message = "CcNumber is requierd")
    private String ccNumber;
    @NotBlank(message = "ccExpiration is requierd")
    private String ccExpiration;
    @NotBlank(message = "ccCVV is requierd")
    private String ccCVV;
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> taco = new ArrayList<>();
    private Date placedAt;


    public Order(String deliveryName, String deliveryStreet, String deliveryCity, String deliveryState, String deliveryZip, String ccNumber, String ccExpiration, String ccCVV, Date placedAt) {
        this.deliveryName = deliveryName;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
        this.placedAt = placedAt;
    }

    public Date getPlacedAt() {
        return placedAt;
    }
    @PrePersist
    public void setPlacedAt() {
        this.placedAt = new Date();
    }

    public Order(long id, String name, String street, String city, String state, String zip, String ccNumber, String ccExpiration, String ccCVV) {
        this.id = id;
        this.deliveryName = name;
        this.deliveryStreet = street;
        this.deliveryCity = city;
        this.deliveryState = state;
        this.deliveryZip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
    }
    public Order(String name, String street, String city, String state, String zip, String ccNumber, String ccExpiration, String ccCVV, String cccvv, String placedAt) {

        this.deliveryName = name;
        this.deliveryStreet = street;
        this.deliveryCity = city;
        this.deliveryState = state;
        this.deliveryZip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;

    }
    public Order(String name, String street, String city, String state, String zip, String ccNumber, String ccExpiration, String ccCVV, String cccvv ,Date createdAt,List<Taco> taco) {

        this.deliveryName = name;
        this.deliveryStreet = street;
        this.deliveryCity = city;
        this.deliveryState = state;
        this.deliveryZip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
        this.taco = taco;

    }
    public Order(){

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getdeliveryName() {
        return deliveryName;
    }

    public void setdeliveryName(String name) {
        this.deliveryName = name;
    }

    public String getdeliveryStreet() {
        return deliveryStreet;
    }

    public void setdeliveryStreet(String street) {
        this.deliveryStreet = street;
    }

    public String getdeliveryCity() {
        return deliveryCity;
    }

    public void setdeliveryCity(String city) {
        this.deliveryCity = city;
    }

    public String getdeliveryState() {
        return deliveryState;
    }

    public void setdeliveryState(String state) {
        this.deliveryState = state;
    }

    public String getdeliveryZip() {
        return deliveryZip;
    }

    public void setdeliveryZip(String zip) {
        this.deliveryZip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<Taco> getTaco() {
        return taco;
    }

    public void setTaco(Taco taco) {
        this.taco.add(taco);
    }
}


