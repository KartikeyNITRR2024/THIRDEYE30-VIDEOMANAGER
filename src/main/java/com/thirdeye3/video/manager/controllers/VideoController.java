package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.EndingDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.services.EndingService;
import com.thirdeye3.video.manager.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vm/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
    
    @Autowired
    private EndingService endingService;
    
    @GetMapping("/ending")
    public Response<EndingDto> getEnding() {
        return new Response<>(true, 0, null, endingService.getEnding());
    }
    
    @PutMapping
    public Response<EndingDto> updateEnding(@RequestBody EndingDto endingDto) {
        return new Response<>(true, 0, null, endingService.updateEnding(endingDto.getVideoId()));
    }

    @PostMapping
    public Response<VideoDto> createVideo(@RequestBody VideoDto videoDto) {
        return new Response<>(true, 0, null, videoService.createVideo(videoDto));
    }

    @GetMapping("/{id}")
    public Response<VideoDto> getVideoById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, videoService.getVideoById(id));
    }

    @GetMapping
    public Response<List<VideoDto>> getAllVideos() {
        return new Response<>(true, 0, null, videoService.getAllVideos());
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteVideo(@PathVariable UUID id) {
        videoService.deleteVideo(id);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/{id}/note")
    public Response<VideoDto> updateNote(@PathVariable UUID id, @RequestBody VideoDto videoDto) {
        return new Response<>(true, 0, null, videoService.updateNote(id, videoDto.getNote()));
    }

    @PatchMapping("/{id}/completed")
    public Response<VideoDto> updateCompleted(@PathVariable UUID id, @RequestBody VideoDto videoDto) {
        return new Response<>(true, 0, null, videoService.updateCompleted(id, videoDto.getCompleted()));
    }

    @PatchMapping("/{id}/current-state")
    public Response<VideoDto> updateCurrentState(@PathVariable UUID id, @RequestBody VideoDto videoDto) {
        return new Response<>(true, 0, null, videoService.updateCurrentState(id, videoDto.getCurrentState()));
    }
}