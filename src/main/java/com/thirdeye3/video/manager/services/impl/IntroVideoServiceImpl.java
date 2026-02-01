package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.IntroVideoDto;
import com.thirdeye3.video.manager.entities.IntroVideo;
import com.thirdeye3.video.manager.repositories.IntroVideoRepository;
import com.thirdeye3.video.manager.services.IntroVideoService;
import com.thirdeye3.video.manager.utils.Mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntroVideoServiceImpl implements IntroVideoService {

    private static final Logger logger = LoggerFactory.getLogger(IntroVideoServiceImpl.class);

    @Autowired
    private IntroVideoRepository introVideoRepository;

    private Mapper mapper = new Mapper(); 

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public IntroVideoDto createIntroVideo(IntroVideoDto dto) {
        logger.info("Creating IntroVideo with name: {}", dto.getIntroName());
        IntroVideo intro = mapper.mapDtoToEntity(dto);

        if (intro.getActive() == null) {
            intro.setActive(false);
        }

        IntroVideo savedIntro = introVideoRepository.save(intro);
        logger.info("IntroVideo created with id: {}", savedIntro.getIntroId());
        return mapper.mapToDto(savedIntro);
    }

    @Override
    public IntroVideoDto getIntroVideoById(Long id) {
        logger.info("Fetching IntroVideo with id: {}", id);
        IntroVideo intro = introVideoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("IntroVideo not found with id: {}", id);
                    return new ResourceNotFoundException("Intro Video not found with ID: " + id);
                });
        return mapper.mapToDto(intro);
    }

    @Override
    public List<IntroVideoDto> getAllIntroVideos() {
        logger.info("Fetching all IntroVideos ordered by active and lastlyUsed");
        List<IntroVideoDto> intros = introVideoRepository.findAllByOrderByActiveDescLastlyUsedDesc().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        logger.info("Total IntroVideos found: {}", intros.size());
        return intros;
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public IntroVideoDto updateIntroVideo(Long id, IntroVideoDto dto) {
        logger.info("Updating IntroVideo with id: {}", id);

        IntroVideo intro = introVideoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("IntroVideo not found with id: {}", id);
                    return new ResourceNotFoundException("Intro Video not found with ID: " + id);
                });

        intro = mapper.mapDtoToEntity(dto);
        intro.setIntroId(id);

        IntroVideo updatedIntro = introVideoRepository.save(intro);
        logger.info("IntroVideo updated with id: {}", updatedIntro.getIntroId());
        return mapper.mapToDto(updatedIntro);
    }

    @Override
    @CacheEvict(value = "combineddata", allEntries = true)
    public void deleteIntroVideo(Long id) {
        logger.info("Deleting IntroVideo with id: {}", id);
        introVideoRepository.deleteById(id);
        logger.info("IntroVideo deleted with id: {}", id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public IntroVideoDto updateActive(Long id) {
        logger.info("Deactivating all IntroVideos");
        introVideoRepository.deactivateAll();

        logger.info("Activating IntroVideo with id: {}", id);
        IntroVideo intro = introVideoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("IntroVideo not found with id: {}", id);
                    return new ResourceNotFoundException("Intro Video not found with ID: " + id);
                });

        intro.setActive(true);
        intro.setLastlyUsed(LocalDateTime.now());

        IntroVideo updatedIntro = introVideoRepository.save(intro);
        logger.info("IntroVideo activated with id: {}", updatedIntro.getIntroId());
        return mapper.mapToDto(updatedIntro);
    }

    @Override
    public IntroVideoDto getActiveIntroVideo() {
        logger.info("Fetching active IntroVideo");
        IntroVideo intro = introVideoRepository.findFirstByActiveTrue()
                .orElseThrow(() -> {
                    logger.error("No active IntroVideo found");
                    return new ResourceNotFoundException("No active Intro Video found");
                });
        return mapper.mapToDto(intro);
    }
}