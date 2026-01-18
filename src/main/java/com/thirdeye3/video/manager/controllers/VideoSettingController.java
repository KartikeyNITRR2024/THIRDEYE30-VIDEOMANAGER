package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.VideoSettingDto;
import com.thirdeye3.video.manager.services.VideoSettingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm/video-settings")
public class VideoSettingController {

    @Autowired
    private VideoSettingService videoSettingService;

    @PostMapping
    public Response<VideoSettingDto> createVideoSetting(@RequestBody VideoSettingDto videoSettingDto) {
        return new Response<>(true, 0, null, videoSettingService.createVideoSetting(videoSettingDto));
    }

    @GetMapping("/{id}")
    public Response<VideoSettingDto> getVideoSettingById(@PathVariable Long id) {
        return new Response<>(true, 0, null, videoSettingService.getVideoSettingById(id));
    }

    @GetMapping
    public Response<List<VideoSettingDto>> getAllVideoSettings() {
        return new Response<>(true, 0, null, videoSettingService.getAllVideoSettings());
    }

    @PutMapping("/{id}")
    public Response<VideoSettingDto> updateVideoSetting(@PathVariable Long id, @RequestBody VideoSettingDto videoSettingDto) {
        return new Response<>(true, 0, null, videoSettingService.updateVideoSetting(id, videoSettingDto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteVideoSetting(@PathVariable Long id) {
        videoSettingService.deleteVideoSetting(id);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/{id}/active")
    public Response<VideoSettingDto> updateActive(@PathVariable Long id) {
        return new Response<>(true, 0, null, videoSettingService.updateActive(id));
    }
}
