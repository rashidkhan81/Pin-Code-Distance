package com.ex.Pincode_Distance.pin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pin_code_entity")
public class PinCodeEntity {

    @Id
    @Column(name = "pincode", nullable = false)
    private String pincode;  // Since pincodes can be alphanumeric in some countries

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "polygon")
    private String polygon;

// Constructors, getters, and setters
}