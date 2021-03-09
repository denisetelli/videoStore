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

import com.videoStore.javaBackEnd.controllers.forms.VideoForm;
import com.videoStore.javaBackEnd.dtos.VideoDto;
import com.videoStore.javaBackEnd.models.Video;
import com.videoStore.javaBackEnd.services.VideoService;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@GetMapping
	public List<VideoDto> getAll() {
		return videoService.getAll();
	}

	@PostMapping
	public ResponseEntity<VideoDto> add(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
		VideoDto video = videoService.add(form);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(video);
	}

	@GetMapping("/{title}")
	public ResponseEntity<List<VideoDto>> getByTitle(@PathVariable String title) {
		List<VideoDto> videosListByTitle = videoService.getByTitle(title);
		if (videosListByTitle != null) {
			return ResponseEntity.ok(videosListByTitle);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<VideoDto> update(@PathVariable Long id, @RequestBody @Valid VideoForm form) {
		Optional<Video> optional = videoService.getOptional(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(videoService.update(id, form));
		}
		return ResponseEntity.notFound().build();
	}

}
