package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.StockDto;
import com.thirdeye3.video.manager.entities.Group;
import com.thirdeye3.video.manager.entities.Stock;
import com.thirdeye3.video.manager.repositories.GroupRepository;
import com.thirdeye3.video.manager.services.GroupService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final Mapper mapper = new Mapper();

    @Override
    @Transactional
    public GroupDto createGroup(GroupDto groupDto) {
        logger.info("Creating new group with name: {}", groupDto.getName());

        Group group = new Group();
        group.setName(groupDto.getName());
        group.setActive(groupDto.getActive());

        if (groupDto.getStocks() != null) {
            logger.info("Adding {} stocks to group: {}", groupDto.getStocks().size(), groupDto.getName());
            for (StockDto stockDto : groupDto.getStocks()) {
                Stock stock = new Stock();
                stock.setMarketCode(stockDto.getMarketCode());
                stock.setStockName(stockDto.getStockName());
                stock.setActive(stockDto.getActive());
                group.addStock(stock);
            }
        } else {
            logger.info("No stocks provided for group: {}", groupDto.getName());
        }

        Group savedGroup = groupRepository.save(group);
        logger.info("Group created successfully with id: {}", savedGroup.getId());

        return mapper.mapToDTO(savedGroup);
    }

    @Override
    public GroupDto getGroupById(Long id) {
        logger.info("Fetching group with id: {}", id);

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Group not found with id: {}", id);
                    return new ResourceNotFoundException("Group not found");
                });

        logger.info("Group found with id: {}", id);
        return mapper.mapToDTO(group);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        logger.info("Fetching all groups");

        List<GroupDto> groups = groupRepository.findAll()
                .stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());

        logger.info("Total groups found: {}", groups.size());
        return groups;
    }

    @Override
    @Transactional
    public GroupDto updateGroup(Long id, GroupDto groupDto) {
        logger.info("Updating group with id: {}", id);

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Group not found for update with id: {}", id);
                    return new ResourceNotFoundException("Group not found");
                });

        group.setName(groupDto.getName());
        group.setActive(groupDto.getActive());

        if (groupDto.getStocks() != null) {
            logger.info("Replacing stocks for group id: {} | New count: {}", id, groupDto.getStocks().size());

            group.getStocks().clear();

            for (StockDto stockDto : groupDto.getStocks()) {
                Stock stock = new Stock();
                stock.setMarketCode(stockDto.getMarketCode());
                stock.setStockName(stockDto.getStockName());
                stock.setActive(stockDto.getActive());
                group.addStock(stock);
            }
        }

        Group updatedGroup = groupRepository.save(group);
        logger.info("Group updated successfully with id: {}", updatedGroup.getId());

        return mapper.mapToDTO(updatedGroup);
    }

    @Override
    @Transactional
    public void deleteGroup(Long id) {
        logger.info("Deleting group with id: {}", id);
        groupRepository.deleteById(id);
        logger.info("Group deleted successfully with id: {}", id);
    }
}
