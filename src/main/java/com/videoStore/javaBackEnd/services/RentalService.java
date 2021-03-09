package com.videoStore.javaBackEnd.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videoStore.javaBackEnd.controllers.forms.RentalForm;
import com.videoStore.javaBackEnd.dtos.RentalDto;
import com.videoStore.javaBackEnd.models.Rental;
import com.videoStore.javaBackEnd.models.User;
import com.videoStore.javaBackEnd.repositories.RentalRepository;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;
	@Autowired
	private VideoService videoService;
	@Autowired
	private UserService userService;

	public List<RentalDto> getAll() {
		List<Rental> rentals = rentalRepository.findAll();
		return RentalDto.convert(rentals);
	}

	@Transactional
	public RentalDto rent(RentalForm form) {
		Rental rental = form.convert();
		rental.setDateTime(LocalDateTime.now());
		User user = rental.getUser();
		userService.associateRental(user);
		rental.setUser(user);
		videoService.rentVideos(rental.getVideo());
		rentalRepository.save(rental);
		return new RentalDto(rental);
	}

	@Transactional
	public RentalDto update(Long id, RentalForm form) {
		Rental rental = rentalRepository.getOne(id);
		rental.setUser(form.getUser());
		rental.setDateTime(form.getDateTime());
		rental.setVideo(form.getVideos());
		return new RentalDto(rental);
	}

	@Transactional
	public RentalDto endRental(Long id) {
		Rental rental = rentalRepository.getOne(id);
		rental.setDateTime(LocalDateTime.now());
		videoService.endVideosRental(rental.getVideo());
		return new RentalDto(rental);
	}

	public Optional<Rental> getOptional(Long id) {
		return rentalRepository.findById(id);
	}

}
