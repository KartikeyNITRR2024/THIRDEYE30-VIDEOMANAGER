package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.IntroVideoDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.IntroVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm/intro-videos")
public class IntroVideoController {

    @Autowired
    private IntroVideoService introVideoService;

    @PostMapping
    public Response<IntroVideoDto> createIntroVideo(@RequestBody IntroVideoDto dto) {
        return new Response<>(true, 0, null, introVideoService.createIntroVideo(dto));
    }

    @GetMapping("/{id}")
    public Response<IntroVideoDto> getIntroVideoById(@PathVariable Long id) {
        return new Response<>(true, 0, null, introVideoService.getIntroVideoById(id));
    }

    @GetMapping
    public Response<List<IntroVideoDto>> getAllIntroVideos() {
        return new Response<>(true, 0, null, introVideoService.getAllIntroVideos());
    }

    @PutMapping("/{id}")
    public Response<IntroVideoDto> updateIntroVideo(@PathVariable Long id, @RequestBody IntroVideoDto dto) {
        return new Response<>(true, 0, null, introVideoService.updateIntroVideo(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteIntroVideo(@PathVariable Long id) {
        introVideoService.deleteIntroVideo(id);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/{id}/active")
    public Response<IntroVideoDto> updateActive(@PathVariable Long id) {
        return new Response<>(true, 0, null, introVideoService.updateActive(id));
    }

    @GetMapping("/active")
    public Response<IntroVideoDto> getActiveIntroVideo() {
        return new Response<>(true, 0, null, introVideoService.getActiveIntroVideo());
    }
}