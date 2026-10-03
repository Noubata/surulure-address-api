package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.LookupRequest;
import com.example.demo.dtos.LookupResponse;
import com.example.demo.models.AddressLookupProjection;
import com.example.demo.models.CarreSurulereRepository;

@Service
public class LookupService {

	private final CarreSurulereRepository repository;

	public LookupService(CarreSurulereRepository repository) {
		this.repository = repository;
	}

	public LookupResponse lookup(LookupRequest request) {
		return repository.findContainingAddress(request.lat(), request.lon())
				.map(this::toResponse)
				.orElseGet(LookupResponse::notFound);
	}

	private LookupResponse toResponse(AddressLookupProjection address) {
		return LookupResponse.found(address.getAddressCode(), address.getName());
	}
}