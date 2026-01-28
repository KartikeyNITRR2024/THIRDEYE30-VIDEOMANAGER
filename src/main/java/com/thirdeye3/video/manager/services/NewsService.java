package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.entities.FileMetadata;
import com.thirdeye3.video.manager.projections.NewsAudioProjection;

import java.util.List;
import java.util.UUID;

public interface NewsService {
    NewsDto createNews(NewsDto newsDto);
    NewsDto getNewsById(Long id);
    List<NewsDto> getAllNews();
    NewsDto updateNews(Long id, NewsDto newsDto);
    void deleteNews(Long id);
    List<NewsDto> getNewsByVideoId(UUID videoId);
	List<NewsAudioProjection> pendingNewsToGenerateSound(UUID videoId);
	void linkAudioToNews(Long newsId, FileMetadata file);
}
