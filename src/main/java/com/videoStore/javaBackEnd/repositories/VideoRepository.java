package com.videoStore.javaBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videoStore.javaBackEnd.models.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{


}
