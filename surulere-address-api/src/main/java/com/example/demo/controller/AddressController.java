package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AddressResponse;
import com.example.demo.services.ReverseLookupService;

@RestController
public class AddressController {

	private final ReverseLookupService reverseLookupService;

	public AddressController(ReverseLookupService reverseLookupService) {
		this.reverseLookupService = reverseLookupService;
	}

	@GetMapping("/address/{code}")
	public AddressResponse address(@PathVariable String code) {
		return reverseLookupService.find(code);
	}
}
