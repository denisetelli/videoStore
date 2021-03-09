package com.videoStore.javaBackEnd.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.videoStore.javaBackEnd.models.Rental;

import lombok.Data;

@Data
public class RentalDto {

	private Long id;
	private UserDto user;
	private LocalDateTime dateTime;
	private List<VideoDto> video;

	public RentalDto(Rental rental) {
		this.id = rental.getId();
		this.user = new UserDto(rental.getUser());
		this.dateTime = rental.getDateTime();
		this.video = VideoDto.convert(rental.getVideo());
	}

	public static List<RentalDto> convert(List<Rental> rentals) {
		return rentals.stream().map(RentalDto::new).collect(Collectors.toList());
	}

}
