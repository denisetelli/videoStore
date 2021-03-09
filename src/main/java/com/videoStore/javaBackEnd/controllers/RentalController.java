package com.videoStore.javaBackEnd.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.videoStore.javaBackEnd.controllers.forms.RentalForm;
import com.videoStore.javaBackEnd.dtos.RentalDto;
import com.videoStore.javaBackEnd.models.Rental;
import com.videoStore.javaBackEnd.services.RentalService;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@GetMapping
	public List<RentalDto> getAll() {
		return rentalService.getAll();
	}

	@PostMapping
	public ResponseEntity<RentalDto> rent(@RequestBody @Valid RentalForm form, UriComponentsBuilder uriBuilder) {
		RentalDto rental = rentalService.rent(form);
		URI uri = uriBuilder.path("/locacoes/{id}").buildAndExpand(rental.getId()).toUri();
		return ResponseEntity.created(uri).body(rental);
	}

	@PutMapping("/end/{id}")
	public ResponseEntity<RentalDto> endRental(@PathVariable Long id) {
		Optional<Rental> optional = rentalService.getOptional(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(rentalService.endRental(id));
		}
		return ResponseEntity.notFound().build();
	}

}
