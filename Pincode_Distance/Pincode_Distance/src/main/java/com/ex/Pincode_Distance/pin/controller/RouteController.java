package com.ex.Pincode_Distance.pin.controller;

import com.ex.Pincode_Distance.pin.model.RouteEntity;
import com.ex.Pincode_Distance.pin.model.RouteResponse;
import com.ex.Pincode_Distance.pin.repository.RouteRepository;
import com.ex.Pincode_Distance.pin.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/distance")
public class RouteController {

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private RouteRepository routeRepository;

    // GET endpoint to fetch distance and duration between two pin codes
    @GetMapping
    public ResponseEntity<RouteResponse> getDistanceAndDuration(@RequestParam String fromPincode, @RequestParam String toPincode) {
        // Check if the route is cached in the database
        Optional<RouteEntity> cachedRoute = routeRepository.findByFromPincodeAndToPincode(fromPincode, toPincode);

        if (cachedRoute.isPresent()) {
            RouteEntity routeEntity = cachedRoute.get();
            RouteResponse response = new RouteResponse(
                    routeEntity.getFromPincode(),
                    routeEntity.getToPincode(),
                    routeEntity.getDistance(),
                    routeEntity.getDuration(),
                    routeEntity.getRoutes());
            return ResponseEntity.ok(response);
        }

        // If not cached, get the data from Google Maps API
        RouteResponse routeResponse = googleMapsService.getRouteInfo(fromPincode, toPincode);

        // Save new route in the database
        RouteEntity newRoute = new RouteEntity();
        newRoute.setFromPincode(fromPincode);
        newRoute.setToPincode(toPincode);
        newRoute.setDistance(routeResponse.getDistance());
        newRoute.setDuration(routeResponse.getDuration());
        newRoute.setRoutes(routeResponse.getRoutes());

        routeRepository.save(newRoute);

        return ResponseEntity.ok(routeResponse);
    }

    // POST endpoint to save a route explicitly
    @PostMapping("/save")
    public ResponseEntity<String> saveRoute(@RequestBody RouteEntity routeEntity) {
        routeRepository.save(routeEntity);
        return ResponseEntity.ok("Route saved successfully");
    }
}