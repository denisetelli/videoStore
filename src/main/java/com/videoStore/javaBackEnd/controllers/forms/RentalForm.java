package com.videoStore.javaBackEnd.controllers.forms;

import java.time.LocalDateTime;
import java.util.List;

import com.videoStore.javaBackEnd.models.Rental;
import com.videoStore.javaBackEnd.models.User;
import com.videoStore.javaBackEnd.models.Video;

import lombok.Data;

@Data
public class RentalForm {

	private User user;
	private LocalDateTime dateTime = LocalDateTime.now();
	private List<Video> videos;

	public Rental convert() {
		return new Rental(user, dateTime, videos);
	}

}
