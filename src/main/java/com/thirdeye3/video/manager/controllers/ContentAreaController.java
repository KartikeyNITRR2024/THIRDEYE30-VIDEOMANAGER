package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.ContentAreaDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.ContentAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm/content-areas")
public class ContentAreaController {

    @Autowired
    private ContentAreaService contentAreaService;

    @PostMapping
    public Response<ContentAreaDto> create(@RequestBody ContentAreaDto dto) {
        return new Response<>(true, 0, null,
                contentAreaService.createContentArea(dto));
    }

    @GetMapping("/{id}")
    public Response<ContentAreaDto> getById(@PathVariable Long id) {
        return new Response<>(true, 0, null,
                contentAreaService.getContentAreaById(id));
    }

    @GetMapping
    public Response<List<ContentAreaDto>> getAll() {
        return new Response<>(true, 0, null,
                contentAreaService.getAllContentAreas());
    }

    @PutMapping("/{id}")
    public Response<ContentAreaDto> update(
            @PathVariable Long id,
            @RequestBody ContentAreaDto dto) {

        return new Response<>(true, 0, null,
                contentAreaService.updateContentArea(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable Long id) {
        contentAreaService.deleteContentArea(id);
        return new Response<>(true, 0, null, "Deleted Successfully");
    }

    @PostMapping("/{id}/activate")
    public Response<String> activate(@PathVariable Long id) {
        contentAreaService.activateContentArea(id);
        return new Response<>(true, 0, null, "Activated Successfully");
    }
    
    @GetMapping("/active")
    public Response<ContentAreaDto> getActive() {
        return new Response<>(true, 0, null,
                contentAreaService.getActiveContentArea());
    }

}
