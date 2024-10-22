package com.ex.Pincode_Distance.pin.service;

import com.ex.Pincode_Distance.pin.model.RouteResponse;
import lombok.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {
    private String googleApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value = "routeCache", key = "#fromPincode + '-' + #toPincode")
    public RouteResponse getRouteInfo(String fromPincode, String toPincode) {
        String url = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                fromPincode, toPincode, googleApiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return parseGoogleResponse(response.getBody(), fromPincode, toPincode);
        } else {
            throw new RuntimeException("Failed to fetch data from Google Maps API");
        }
    }

    private RouteResponse parseGoogleResponse(String responseBody, String fromPincode, String toPincode) {
        // Parse response and extract distance, duration, and routes
        // Map JSON to RouteResponse object and return it
        return null;
    }
}
