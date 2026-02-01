package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.StockDto;
import com.thirdeye3.video.manager.entities.Group;
import com.thirdeye3.video.manager.entities.Stock;
import com.thirdeye3.video.manager.repositories.GroupRepository;
import com.thirdeye3.video.manager.repositories.StockRepository;
import com.thirdeye3.video.manager.services.StockService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private GroupRepository groupRepository;
    
    private final Mapper mapper = new Mapper();

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public StockDto createStock(Long groupId, StockDto stockDto) {
        logger.info("Creating stock under groupId: {}", groupId);

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    logger.error("Group not found with id: {}", groupId);
                    return new ResourceNotFoundException("Group not found with id: " + groupId);
                });

        Stock stock = new Stock();
        stock.setMarketCode(stockDto.getMarketCode());
        stock.setStockName(stockDto.getStockName());
        stock.setActive(stockDto.getActive());

        group.addStock(stock);

        Stock savedStock = stockRepository.save(stock);

        logger.info("Stock created successfully with id: {} under groupId: {}", savedStock.getId(), groupId);

        return mapper.mapToDTO(savedStock);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public StockDto updateStock(Long stockId, StockDto stockDto) {
        logger.info("Updating stock with id: {}", stockId);

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> {
                    logger.error("Stock not found with id: {}", stockId);
                    return new ResourceNotFoundException("Stock not found with id: " + stockId);
                });

        stock.setMarketCode(stockDto.getMarketCode());
        stock.setStockName(stockDto.getStockName());
        stock.setActive(stockDto.getActive());

        Stock updatedStock = stockRepository.save(stock);

        logger.info("Stock updated successfully with id: {}", updatedStock.getId());

        return mapper.mapToDTO(updatedStock);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", allEntries = true)
    public void deleteStock(Long stockId) {
        logger.info("Deleting stock with id: {}", stockId); 
        stockRepository.deleteById(stockId);
        logger.info("Stock deleted successfully with id: {}", stockId);
    }

    @Override
    public StockDto getStockById(Long stockId) {
        logger.info("Fetching stock with id: {}", stockId);

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> {
                    logger.error("Stock not found with id: {}", stockId);
                    return new ResourceNotFoundException("Stock not found with id: " + stockId);
                });

        logger.info("Stock found with id: {}", stockId);

        return mapper.mapToDTO(stock);
    }
}