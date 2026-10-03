package com.example.demo.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarreSurulereRepository extends JpaRepository<CarreSurulere, String> {

	@Query(value = """
			SELECT address_code AS "addressCode", name AS "name"
			FROM carre_surulere
			WHERE ST_Contains(geom, ST_SetSRID(ST_MakePoint(:lon, :lat), 4326))
			LIMIT 1
			""", nativeQuery = true)
	Optional<AddressLookupProjection> findContainingAddress(
			@Param("lat") double lat,
			@Param("lon") double lon);
}