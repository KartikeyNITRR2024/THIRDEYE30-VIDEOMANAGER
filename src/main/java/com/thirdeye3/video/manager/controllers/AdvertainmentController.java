package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.AdvertainmentDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.AdvertainmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm/advertainments")
public class AdvertainmentController {

    @Autowired
    private AdvertainmentService advertainmentService;

    @PostMapping
    public Response<AdvertainmentDto> createAdvertainment(@RequestBody AdvertainmentDto dto) {
        return new Response<>(true, 0, null, advertainmentService.createAdvertainment(dto));
    }

    @GetMapping("/{id}")
    public Response<AdvertainmentDto> getAdvertainmentById(@PathVariable Long id) {
        return new Response<>(true, 0, null, advertainmentService.getAdvertainmentById(id));
    }

    @GetMapping
    public Response<List<AdvertainmentDto>> getAllAdvertainments() {
        return new Response<>(true, 0, null, advertainmentService.getAllAdvertainments());
    }

    @PutMapping("/{id}")
    public Response<AdvertainmentDto> updateAdvertainment(@PathVariable Long id, @RequestBody AdvertainmentDto dto) {
        return new Response<>(true, 0, null, advertainmentService.updateAdvertainment(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteAdvertainment(@PathVariable Long id) {
        advertainmentService.deleteAdvertainment(id);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/{id}/active")
    public Response<AdvertainmentDto> updateActive(@PathVariable Long id) {
        return new Response<>(true, 0, null, advertainmentService.updateActive(id));
    }

    @GetMapping("/active")
    public Response<AdvertainmentDto> getActiveAdvertainment() {
        return new Response<>(true, 0, null, advertainmentService.getActiveAdvertainment());
    }
}
