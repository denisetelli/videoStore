package com.videoStore.javaBackEnd.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.videoStore.javaBackEnd.enums.VideoStatus;
import com.videoStore.javaBackEnd.models.Video;

import lombok.Data;

@Data
public class VideoDto {
	private Long id;
	private String title;
	private String director;
	private VideoStatus status;

	public VideoDto(Video video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.director = video.getDirector();
		this.status = video.getStatus();
	}

	public static List<VideoDto> convert(List<Video> videos) {
		return videos.stream().map(VideoDto::new).collect(Collectors.toList());
	}
}
