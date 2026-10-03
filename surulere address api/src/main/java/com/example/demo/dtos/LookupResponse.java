package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LookupResponse(
		boolean found,
		@JsonProperty("address_code") String addressCode,
		String quartier,
		Integer arrondissement,
		String message) {

	public static LookupResponse found(String addressCode, String quartier) {
		return new LookupResponse(true, addressCode, quartier, 4, null);
	}

	public static LookupResponse notFound() {
		return new LookupResponse(false, null, null, null,
				"No address found for this coordinate");
	}
}