package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3.video.manager.services.VideoDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vm/video-details")
public class VideoDetailsController {

    @Autowired
    private VideoDetailsService videoDetailsService;

    @PostMapping
    public Response<VideoDetailsDto> createVideoDetails(@RequestBody VideoDetailsDto dto) {
        return new Response<>(true, 0, null, videoDetailsService.createVideoDetails(dto));
    }

    @GetMapping("/{id}")
    public Response<VideoDetailsDto> getVideoDetailsById(@PathVariable Long id) {
        return new Response<>(true, 0, null, videoDetailsService.getVideoDetailsById(id));
    }

    @GetMapping
    public Response<List<VideoDetailsDto>> getAllVideoDetails() {
        return new Response<>(true, 0, null, videoDetailsService.getAllVideoDetails());
    }

    @PutMapping("/{id}")
    public Response<VideoDetailsDto> updateVideoDetails(@PathVariable Long id, @RequestBody VideoDetailsDto dto) {
        return new Response<>(true, 0, null, videoDetailsService.updateVideoDetails(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteVideoDetails(@PathVariable Long id) {
        videoDetailsService.deleteVideoDetails(id);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/{id}/active")
    public Response<VideoDetailsDto> updateActive(@PathVariable Long id) {
        return new Response<>(true, 0, null, videoDetailsService.updateActive(id));
    }

    @GetMapping("/active")
    public Response<VideoDetailsDto> getActiveVideoDetails() {
        return new Response<>(true, 0, null, videoDetailsService.getActiveVideoDetails());
    }

    @GetMapping("/video/{videoId}")
    public Response<VideoDetailsDto> getVideoDetailsByVideoId(@PathVariable UUID videoId) {
        return new Response<>(true, 0, null, videoDetailsService.getVideoDetailsByVideoId(videoId));
    }
}