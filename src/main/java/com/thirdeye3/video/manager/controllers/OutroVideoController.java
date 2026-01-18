package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.OutroVideoDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.OutroVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm/outro-videos")
public class OutroVideoController {

    @Autowired
    private OutroVideoService outroVideoService;

    @PostMapping
    public Response<OutroVideoDto> createOutroVideo(@RequestBody OutroVideoDto dto) {
        return new Response<>(true, 0, null, outroVideoService.createOutroVideo(dto));
    }

    @GetMapping("/{id}")
    public Response<OutroVideoDto> getOutroVideoById(@PathVariable Long id) {
        return new Response<>(true, 0, null, outroVideoService.getOutroVideoById(id));
    }

    @GetMapping
    public Response<List<OutroVideoDto>> getAllOutroVideos() {
        return new Response<>(true, 0, null, outroVideoService.getAllOutroVideos());
    }

    @PutMapping("/{id}")
    public Response<OutroVideoDto> updateOutroVideo(@PathVariable Long id, @RequestBody OutroVideoDto dto) {
        return new Response<>(true, 0, null, outroVideoService.updateOutroVideo(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteOutroVideo(@PathVariable Long id) {
        outroVideoService.deleteOutroVideo(id);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/{id}/active")
    public Response<OutroVideoDto> updateActive(@PathVariable Long id) {
        return new Response<>(true, 0, null, outroVideoService.updateActive(id));
    }

    @GetMapping("/active")
    public Response<OutroVideoDto> getActiveOutroVideo() {
        return new Response<>(true, 0, null, outroVideoService.getActiveOutroVideo());
    }
}