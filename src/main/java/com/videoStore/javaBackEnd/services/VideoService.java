package com.videoStore.javaBackEnd.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Enumerated;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videoStore.javaBackEnd.controllers.forms.VideoForm;
import com.videoStore.javaBackEnd.dtos.VideoDto;
import com.videoStore.javaBackEnd.enums.VideoStatus;
import com.videoStore.javaBackEnd.models.Video;
import com.videoStore.javaBackEnd.repositories.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;
	@Enumerated
	private VideoStatus rentalStatusEnum;

	@Transactional
	public VideoDto add(VideoForm form) {
		Video video = form.convert();
		videoRepository.save(video);
		return new VideoDto(video);
	}

	public List<VideoDto> getByTitle(String titleToSearch) {
		List<VideoDto> videosByTitle = new ArrayList<>();
		for (VideoDto video : getAll()) {
			titleToSearch = titleToSearch.replace('+', ' ');
			if (titleToSearch.equalsIgnoreCase(video.getTitle())) {
				videosByTitle.add(video);
			}
		}
		return videosByTitle;
	}

	public List<VideoDto> getAll() {
		List<Video> videos = videoRepository.findAll();
		return VideoDto.convert(videos);
	}

	public Optional<Video> getOptional(Long id) {
		return videoRepository.findById(id);
	}

	@Transactional
	public VideoDto update(Long id, VideoForm form) {
		Video video = form.update(id, videoRepository);
		return new VideoDto(video);
	}

	public void rentVideos(List<Video> videos) {
		videos.forEach(video -> rent(video.getId()));
	}

	@Transactional
	private void rent(Long id) {
		Video video = videoRepository.getOne(id);
		video.setStatus(VideoStatus.ALUGADO);
		videoRepository.save(video);
	}

	public void endVideosRental(List<Video> videos) {
		videos.forEach(video -> endRental(video.getId()));
	}

	@Transactional
	private void endRental(Long id) {
		Video video = videoRepository.getOne(id);
		video.setStatus(VideoStatus.DISPONIVEL);
		videoRepository.save(video);
	}

}
