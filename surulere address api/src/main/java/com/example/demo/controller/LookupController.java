package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.LookupErrorResponse;
import com.example.demo.dtos.LookupRequest;
import com.example.demo.dtos.LookupResponse;
import com.example.demo.services.LookupService;

@RestController
public class LookupController {

	private final LookupService lookupService;

	public LookupController(LookupService lookupService) {
		this.lookupService = lookupService;
	}

	@GetMapping("/lookup")
	public ResponseEntity<?> lookup(
			@RequestParam(required = false) String lat,
			@RequestParam(required = false) String lon) {
		if (lat == null || lon == null) {
			return badRequest("Both lat and lon query parameters are required");
		}

		try {
			double latitude = Double.parseDouble(lat);
			double longitude = Double.parseDouble(lon);
			if (!Double.isFinite(latitude) || !Double.isFinite(longitude)
					|| latitude < -90 || latitude > 90
					|| longitude < -180 || longitude > 180) {
				return badRequest("lat and lon must be valid geographic coordinates");
			}
			LookupResponse response = lookupService.lookup(new LookupRequest(latitude, longitude));
			return ResponseEntity.ok(response);
		} catch (NumberFormatException exception) {
			return badRequest("lat and lon must be numeric coordinates");
		}
	}

	private ResponseEntity<LookupErrorResponse> badRequest(String message) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new LookupErrorResponse(message));
	}
}