package com.ex.Pincode_Distance.pin.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {
    private String fromPincode;
    private String toPincode;
    private String distance;
    private String duration;
    private List<String> routes; // Routes as steps from Google Maps
}
