package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.AddressResponse;
import com.example.demo.models.CarreSurulereRepository;

@Service
public class ReverseLookupService {

	private final CarreSurulereRepository repository;

	public ReverseLookupService(CarreSurulereRepository repository) {
		this.repository = repository;
	}

	public AddressResponse find(String code) {
		String normalized = code.trim().toUpperCase();
		return repository.findCenterByAddressCode(normalized)
				.map(center -> AddressResponse.found(normalized, center.getLatitude(), center.getLongitude()))
				.orElseGet(AddressResponse::notFound);
	}
}
