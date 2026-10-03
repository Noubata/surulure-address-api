package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carre_surulere")
public class CarreSurulere {

	@Id
	@Column(name = "address_code")
	private String addressCode;

	@Column(name = "name")
	private String name;

	protected CarreSurulere() {
	}
}