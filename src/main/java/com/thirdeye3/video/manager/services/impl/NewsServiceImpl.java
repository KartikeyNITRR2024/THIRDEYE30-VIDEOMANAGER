package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.entities.News;
import com.thirdeye3.video.manager.entities.Video;
import com.thirdeye3.video.manager.repositories.NewsRepository;
import com.thirdeye3.video.manager.repositories.VideoRepository;
import com.thirdeye3.video.manager.services.NewsService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsRepository newsRepository;
    
    @Autowired
    private VideoRepository videoRepository;

    private Mapper mapper = new Mapper();

    @Override
    @Transactional
    public NewsDto createNews(NewsDto newsDto) {
        logger.info("Creating News with header: {}", newsDto.getHeader());

        News news = mapper.mapDtoToEntity(newsDto);

        if (newsDto.getVideoId() != null) {
            logger.info("Linking News with videoId: {}", newsDto.getVideoId());
            Video video = videoRepository.findById(newsDto.getVideoId())
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", newsDto.getVideoId());
                        return new ResourceNotFoundException("Video not found ID: " + newsDto.getVideoId());
                    });
            news.setVideo(video);
        }

        News savedNews = newsRepository.save(news);
        logger.info("News created with id: {}", savedNews.getNewsId());

        return mapper.mapToDto(savedNews);
    }

    @Override
    public NewsDto getNewsById(Long id) {
        logger.info("Fetching News with id: {}", id);

        News news = newsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("News not found with id: {}", id);
                    return new ResourceNotFoundException("News not found with ID: " + id);
                });

        return mapper.mapToDto(news);
    }

    @Override
    public List<NewsDto> getAllNews() {
        logger.info("Fetching all News");

        List<NewsDto> list = newsRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        logger.info("Total News found: {}", list.size());
        return list;
    }

    @Override
    @Transactional
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        logger.info("Updating News with id: {}", id);

        News news = newsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("News not found with id: {}", id);
                    return new ResourceNotFoundException("News not found with ID: " + id);
                });

        news.setHeader(newsDto.getHeader());
        news.setContent(newsDto.getContent());
        news.setIsImagePresent(newsDto.getIsImagePresent());
        news.setImageUrl(newsDto.getImageUrl());

        if (newsDto.getVideoId() != null) {
            logger.info("Updating video link for News id: {} with videoId: {}", id, newsDto.getVideoId());
            Video video = videoRepository.findById(newsDto.getVideoId())
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", newsDto.getVideoId());
                        return new ResourceNotFoundException("Video not found ID: " + newsDto.getVideoId());
                    });
            news.setVideo(video);
        } else {
            logger.info("Removing video link for News id: {}", id);
            news.setVideo(null);
        }

        News updatedNews = newsRepository.save(news);
        logger.info("News updated with id: {}", updatedNews.getNewsId());

        return mapper.mapToDto(updatedNews);
    }

    @Override
    public void deleteNews(Long id) {
        logger.info("Deleting News with id: {}", id);
        newsRepository.deleteById(id);
        logger.info("News deleted with id: {}", id);
    }
    
    @Override
    public List<NewsDto> getNewsByVideoId(UUID videoId) {
        logger.info("Fetching News by videoId: {}", videoId);
        List<NewsDto> list = newsRepository.findByVideo_Id(videoId).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        logger.info("Total News found for videoId {}: {}", videoId, list.size());
        return list;
    }
}
