package com.videoStore.javaBackEnd.controllers.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.videoStore.javaBackEnd.enums.VideoStatus;
import com.videoStore.javaBackEnd.models.Video;
import com.videoStore.javaBackEnd.repositories.VideoRepository;

import lombok.Data;

@Data
public class VideoForm {

	@NotNull
	@NotEmpty
	private String title;
	@NotNull
	@NotEmpty
	private String director;
	@NotNull
	private VideoStatus status;

	public Video convert() {
		return new Video(title, director, status);
	}

	public Video update(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.getOne(id);
		video.setTitle(this.title);
		video.setDirector(this.director);
		video.setStatus(this.status);
		return video;
	}

	public Video rent(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.getOne(id);
		video.setTitle(this.title);
		video.setDirector(this.director);
		video.setStatus(VideoStatus.ALUGADO);
		return video;
	}

}
