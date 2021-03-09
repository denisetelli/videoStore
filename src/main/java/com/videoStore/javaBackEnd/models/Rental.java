package com.videoStore.javaBackEnd.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Rental {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private LocalDateTime dateTime;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Video> video;

	public Rental() {

	}

	public Rental(User user, LocalDateTime dateTime, List<Video> video) {
		this.user = user;
		this.dateTime = dateTime;
		this.video = video;
	}

}
