package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vm/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping
    public Response<NewsDto> createNews(@RequestBody NewsDto newsDto) {
        return new Response<>(true, 0, null, newsService.createNews(newsDto));
    }

    @GetMapping("/{id}")
    public Response<NewsDto> getNewsById(@PathVariable Long id) {
        return new Response<>(true, 0, null, newsService.getNewsById(id));
    }

    @GetMapping
    public Response<List<NewsDto>> getAllNews() {
        return new Response<>(true, 0, null, newsService.getAllNews());
    }
    
    @GetMapping("/video/{videoId}")
    public Response<List<NewsDto>> getNewsByVideoId(@PathVariable UUID videoId) {
        return new Response<>(true, 0, null, newsService.getNewsByVideoId(videoId));
    }

    @PutMapping("/{id}")
    public Response<NewsDto> updateNews(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        return new Response<>(true, 0, null, newsService.updateNews(id, newsDto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new Response<>(true, 0, null, null);
    }
}
