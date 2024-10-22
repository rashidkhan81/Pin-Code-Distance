package com.ex.Pincode_Distance.pin;

import com.ex.Pincode_Distance.pin.model.RouteResponse;
import com.ex.Pincode_Distance.pin.service.GoogleMapsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PincodeDistanceApplicationTests {


	@Autowired
	private GoogleMapsService googleMapsService;

	@Test
	public void testGetRouteInfo() {
		RouteResponse response = googleMapsService.getRouteInfo("141106", "110060");
		assertNotNull(response);
		assertEquals("141106", response.getFromPincode());
		assertEquals("110060", response.getToPincode());
	}
	@Test
	void contextLoads() {
	}

}
