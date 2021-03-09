package com.videoStore.javaBackEnd.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.videoStore.javaBackEnd.enums.VideoStatus;

import lombok.Data;

@Entity
@Data
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String director;
	@Enumerated(EnumType.STRING)
	private VideoStatus status;

	public Video() {

	}

	public Video(String title, String director, VideoStatus status) {
		super();
		this.title = title;
		this.director = director;
		this.status = status;
	}

}
