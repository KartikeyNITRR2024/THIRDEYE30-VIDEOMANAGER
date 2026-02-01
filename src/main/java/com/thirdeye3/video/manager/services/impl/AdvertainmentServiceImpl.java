package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.AdvertainmentDto;
import com.thirdeye3.video.manager.entities.Advertainment;
import com.thirdeye3.video.manager.repositories.AdvertainmentRepository;
import com.thirdeye3.video.manager.services.AdvertainmentService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertainmentServiceImpl implements AdvertainmentService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertainmentServiceImpl.class);

    @Autowired
    private AdvertainmentRepository advertainmentRepository;

    private Mapper mapper = new Mapper();

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public AdvertainmentDto createAdvertainment(AdvertainmentDto dto) {
        logger.info("Creating Advertainment");
        Advertainment entity = mapper.mapDtoToEntity(dto);

        if (entity.getActive() == null) {
            entity.setActive(false);
        }

        Advertainment savedEntity = advertainmentRepository.save(entity);
        logger.info("Advertainment created with id: {}", savedEntity.getAdId());
        return mapper.mapToDto(savedEntity);
    }

    @Override
    public AdvertainmentDto getAdvertainmentById(Long id) {
        logger.info("Fetching Advertainment with id: {}", id);
        Advertainment entity = advertainmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Advertainment not found with id: {}", id);
                    return new ResourceNotFoundException("Advertainment not found with ID: " + id);
                });
        return mapper.mapToDto(entity);
    }

    @Override
    public List<AdvertainmentDto> getAllAdvertainments() {
        logger.info("Fetching all Advertainments ordered by active and lastUsed");
        List<AdvertainmentDto> ads = advertainmentRepository.findAllByOrderByActiveDescLastUsedDesc().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        logger.info("Total Advertainments found: {}", ads.size());
        return ads;
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public AdvertainmentDto updateAdvertainment(Long id, AdvertainmentDto dto) {
        logger.info("Updating Advertainment with id: {}", id);

        Advertainment entity = advertainmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Advertainment not found with id: {}", id);
                    return new ResourceNotFoundException("Advertainment not found with ID: " + id);
                });

        entity = mapper.mapDtoToEntity(dto);
        entity.setAdId(id);

        Advertainment updatedEntity = advertainmentRepository.save(entity);
        logger.info("Advertainment updated with id: {}", updatedEntity.getAdId());
        return mapper.mapToDto(updatedEntity);
    }

    @Override
    @CacheEvict(value = "combineddata", allEntries = true)
    public void deleteAdvertainment(Long id) {
        logger.info("Deleting Advertainment with id: {}", id);
        advertainmentRepository.deleteById(id);
        logger.info("Advertainment deleted with id: {}", id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public AdvertainmentDto updateActive(Long id) {
        logger.info("Deactivating all Advertainments");
        advertainmentRepository.deactivateAll();

        logger.info("Activating Advertainment with id: {}", id);
        Advertainment entity = advertainmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Advertainment not found with id: {}", id);
                    return new ResourceNotFoundException("Advertainment not found with ID: " + id);
                });

        entity.setActive(true);
        entity.setLastUsed(LocalDateTime.now());

        Advertainment updatedEntity = advertainmentRepository.save(entity);
        logger.info("Advertainment activated with id: {}", updatedEntity.getAdId());
        return mapper.mapToDto(updatedEntity);
    }

    @Override
    public AdvertainmentDto getActiveAdvertainment() {
        logger.info("Fetching active Advertainment");
        Advertainment entity = advertainmentRepository.findFirstByActiveTrue()
                .orElseThrow(() -> {
                    logger.error("No active Advertainment found");
                    return new ResourceNotFoundException("No active Advertainment found");
                });
        return mapper.mapToDto(entity);
    }
}