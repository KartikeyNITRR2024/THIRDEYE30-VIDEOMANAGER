package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.OutroVideoDto;
import com.thirdeye3.video.manager.entities.OutroVideo;
import com.thirdeye3.video.manager.repositories.OutroVideoRepository;
import com.thirdeye3.video.manager.services.OutroVideoService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutroVideoServiceImpl implements OutroVideoService {

    private static final Logger logger = LoggerFactory.getLogger(OutroVideoServiceImpl.class);

    @Autowired
    private OutroVideoRepository outroVideoRepository;

    private Mapper mapper = new Mapper();

    @Override
    @Transactional
    public OutroVideoDto createOutroVideo(OutroVideoDto dto) {
        logger.info("Creating OutroVideo");
        OutroVideo entity = mapper.mapDtoToEntity(dto);

        if (entity.getActive() == null) {
            entity.setActive(false);
        }

        OutroVideo savedEntity = outroVideoRepository.save(entity);
        logger.info("OutroVideo created with id: {}", savedEntity.getOutroId());
        return mapper.mapToDto(savedEntity);
    }

    @Override
    public OutroVideoDto getOutroVideoById(Long id) {
        logger.info("Fetching OutroVideo with id: {}", id);
        OutroVideo entity = outroVideoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("OutroVideo not found with id: {}", id);
                    return new ResourceNotFoundException("Outro Video not found with ID: " + id);
                });
        return mapper.mapToDto(entity);
    }

    @Override
    public List<OutroVideoDto> getAllOutroVideos() {
        logger.info("Fetching all OutroVideos ordered by active and lastUsed");
        List<OutroVideoDto> outros = outroVideoRepository.findAllByOrderByActiveDescLastUsedDesc().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        logger.info("Total OutroVideos found: {}", outros.size());
        return outros;
    }

    @Override
    @Transactional
    public OutroVideoDto updateOutroVideo(Long id, OutroVideoDto dto) {
        logger.info("Updating OutroVideo with id: {}", id);

        OutroVideo entity = outroVideoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("OutroVideo not found with id: {}", id);
                    return new ResourceNotFoundException("Outro Video not found with ID: " + id);
                });

        entity = mapper.mapDtoToEntity(dto);
        entity.setOutroId(id);

        OutroVideo updatedEntity = outroVideoRepository.save(entity);
        logger.info("OutroVideo updated with id: {}", updatedEntity.getOutroId());
        return mapper.mapToDto(updatedEntity);
    }

    @Override
    public void deleteOutroVideo(Long id) {
        logger.info("Deleting OutroVideo with id: {}", id);
        outroVideoRepository.deleteById(id);
        logger.info("OutroVideo deleted with id: {}", id);
    }

    @Override
    @Transactional
    public OutroVideoDto updateActive(Long id) {
        logger.info("Deactivating all OutroVideos");
        outroVideoRepository.deactivateAll();

        logger.info("Activating OutroVideo with id: {}", id);
        OutroVideo entity = outroVideoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("OutroVideo not found with id: {}", id);
                    return new ResourceNotFoundException("Outro Video not found with ID: " + id);
                });

        entity.setActive(true);
        entity.setLastUsed(LocalDateTime.now());

        OutroVideo updatedEntity = outroVideoRepository.save(entity);
        logger.info("OutroVideo activated with id: {}", updatedEntity.getOutroId());
        return mapper.mapToDto(updatedEntity);
    }

    @Override
    public OutroVideoDto getActiveOutroVideo() {
        logger.info("Fetching active OutroVideo");
        OutroVideo entity = outroVideoRepository.findFirstByActiveTrue()
                .orElseThrow(() -> {
                    logger.error("No active OutroVideo found");
                    return new ResourceNotFoundException("No active Outro Video found");
                });
        return mapper.mapToDto(entity);
    }
}

