package com.thirdeye3.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirdeye3.video.manager.dtos.ContentAreaDto;
import com.thirdeye3.video.manager.entities.ContentArea;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3.video.manager.repositories.ContentAreaRepository;
import com.thirdeye3.video.manager.services.ContentAreaService;
import com.thirdeye3.video.manager.utils.Mapper;

@Service
public class ContentAreaServiceImpl implements ContentAreaService {

    private static final Logger logger =
            LoggerFactory.getLogger(ContentAreaServiceImpl.class);

    @Autowired
    private ContentAreaRepository repository;

    private Mapper mapper = new Mapper();

    @Override
    public ContentAreaDto createContentArea(ContentAreaDto dto) {

        logger.info("Creating new ContentArea");

        ContentArea entity = mapper.toEntity(dto);
        entity.setLastUsed(LocalDateTime.now());

        ContentArea saved = repository.save(entity);

        logger.info("ContentArea created with id: {}", saved.getId());

        return mapper.toDTO(saved);
    }

    @Override
    public ContentAreaDto getContentAreaById(Long id) {

        logger.info("Fetching ContentArea by id: {}", id);

        ContentArea entity = repository.findById(id)
            .orElseThrow(() -> {
                logger.error("ContentArea not found with id: {}", id);
                return new ResourceNotFoundException("Content Area not found");
            });

        return mapper.toDTO(entity);
    }

    @Override
    public List<ContentAreaDto> getAllContentAreas() {

        logger.info("Fetching all ContentAreas");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContentAreaDto updateContentArea(Long id, ContentAreaDto dto) {

        logger.info("Updating ContentArea with id: {}", id);

        ContentArea entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Content Area not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setActive(dto.isActive());
        entity.setLastUsed(LocalDateTime.now());

        ContentArea updated = repository.save(entity);

        logger.info("ContentArea updated with id: {}", id);

        return mapper.toDTO(updated);
    }

    @Override
    public void deleteContentArea(Long id) {

        logger.info("Deleting ContentArea with id: {}", id);

        ContentArea entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Content Area not found"));

        repository.delete(entity);

        logger.info("ContentArea deleted with id: {}", id);
    }

    @Override
    @Transactional
    public void activateContentArea(Long id) {

        logger.info("Activating ContentArea with id: {}", id);

        repository.deactivateAll();

        ContentArea target = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Content Area with ID " + id + " not found"));

        target.setActive(true);
        target.setLastUsed(LocalDateTime.now());

        repository.save(target);

        logger.info("ContentArea activated with id: {}", id);
    }
    
    @Override
    public ContentAreaDto getActiveContentArea() {

        logger.info("Fetching active ContentArea");

        ContentArea entity = repository.findActive()
            .orElseThrow(() -> {
                logger.error("No active ContentArea found");
                return new ResourceNotFoundException("No Active Content Area found");
            });

        return mapper.toDTO(entity);
    }
}
