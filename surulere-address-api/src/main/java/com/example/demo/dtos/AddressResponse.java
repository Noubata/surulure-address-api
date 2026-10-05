package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressResponse(
		boolean found,
		@JsonProperty("address_code") String addressCode,
		Double latitude,
		Double longitude,
		String state,
		String lga,
		String message) {

	public static AddressResponse found(String addressCode, double latitude, double longitude) {
		return new AddressResponse(true, addressCode, latitude, longitude, "Lagos", "Surulere", null);
	}

	public static AddressResponse notFound() {
		return new AddressResponse(false, null, null, null, null, null,
				"No address found for this code");
	}
}
