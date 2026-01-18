package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.entities.Stock;
import com.thirdeye3.video.manager.entities.StockData;
import com.thirdeye3.video.manager.entities.Video;
import com.thirdeye3.video.manager.repositories.StockDataRepository;
import com.thirdeye3.video.manager.repositories.StockRepository;
import com.thirdeye3.video.manager.repositories.VideoRepository;
import com.thirdeye3.video.manager.services.StockDataService;
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
public class StockDataServiceImpl implements StockDataService {

    private static final Logger logger = LoggerFactory.getLogger(StockDataServiceImpl.class);

    @Autowired
    private StockDataRepository stockDataRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private StockRepository stockRepository;

    private Mapper mapper = new Mapper();

    @Override
    @Transactional
    public StockDataDto createStockData(StockDataDto dto) {
        logger.info("Creating StockData entry");

        StockData stockData = new StockData();
        stockData.setPastData(dto.getPastData());

        if (dto.getVideoId() != null) {
            logger.info("Linking StockData with videoId: {}", dto.getVideoId());
            Video video = videoRepository.findById(dto.getVideoId())
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", dto.getVideoId());
                        return new ResourceNotFoundException("Video not found ID: " + dto.getVideoId());
                    });
            stockData.setVideo(video);
        }

        if (dto.getStockId() != null) {
            logger.info("Linking StockData with stockId: {}", dto.getStockId());
            Stock stock = stockRepository.findById(dto.getStockId())
                    .orElseThrow(() -> {
                        logger.error("Stock not found with id: {}", dto.getStockId());
                        return new ResourceNotFoundException("Stock not found ID: " + dto.getStockId());
                    });
            stockData.setStock(stock);
        }

        StockData savedData = stockDataRepository.save(stockData);
        logger.info("StockData created with id: {}", savedData.getId());

        return mapper.mapToDto(savedData);
    }

    @Override
    public StockDataDto getStockDataById(Long id) {
        logger.info("Fetching StockData with id: {}", id);

        StockData stockData = stockDataRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("StockData not found with id: {}", id);
                    return new ResourceNotFoundException("Stock Data not found ID: " + id);
                });

        return mapper.mapToDto(stockData);
    }

    @Override
    public List<StockDataDto> getAllStockData() {
        logger.info("Fetching all StockData");

        List<StockDataDto> list = stockDataRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        logger.info("Total StockData records found: {}", list.size());
        return list;
    }

    @Override
    @Transactional
    public StockDataDto updateStockData(Long id, StockDataDto dto) {
        logger.info("Updating StockData with id: {}", id);

        StockData stockData = stockDataRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("StockData not found with id: {}", id);
                    return new ResourceNotFoundException("Stock Data not found ID: " + id);
                });

        stockData.setPastData(dto.getPastData());

        if (dto.getVideoId() != null) {
            logger.info("Updating video link for StockData id: {}", id);
            Video video = videoRepository.findById(dto.getVideoId())
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", dto.getVideoId());
                        return new ResourceNotFoundException("Video not found ID: " + dto.getVideoId());
                    });
            stockData.setVideo(video);
        }

        if (dto.getStockId() != null) {
            logger.info("Updating stock link for StockData id: {}", id);
            Stock stock = stockRepository.findById(dto.getStockId())
                    .orElseThrow(() -> {
                        logger.error("Stock not found with id: {}", dto.getStockId());
                        return new ResourceNotFoundException("Stock not found ID: " + dto.getStockId());
                    });
            stockData.setStock(stock);
        }

        StockData updatedData = stockDataRepository.save(stockData);
        logger.info("StockData updated with id: {}", updatedData.getId());

        return mapper.mapToDto(updatedData);
    }

    @Override
    public void deleteStockData(Long id) {
        logger.info("Deleting StockData with id: {}", id);
        stockDataRepository.deleteById(id);
        logger.info("StockData deleted with id: {}", id);
    }

    @Override
    public List<StockDataDto> getStockDataByVideoId(UUID videoId) {
        logger.info("Fetching StockData by videoId: {}", videoId);

        List<StockDataDto> list = stockDataRepository.findByVideo_Id(videoId).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        logger.info("Total StockData records found for videoId {}: {}", videoId, list.size());
        return list;
    }

    @Override
    public List<StockDataDto> getStockDataByStockId(Long stockId) {
        logger.info("Fetching StockData by stockId: {}", stockId);

        List<StockDataDto> list = stockDataRepository.findByStock_Id(stockId).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        logger.info("Total StockData records found for stockId {}: {}", stockId, list.size());
        return list;
    }

	@Override
	public void createStocksData(List<StockDataDto> dtoList) {
		int count = 0;
		for(StockDataDto stockDataDto : dtoList)
		{
			try {
				createStockData(stockDataDto);
				count++;
			} 
			catch(Exception exception)
			{
				logger.info("Falied to add {} ", stockDataDto);
			}
		}
		logger.info("Updated {} out of {} data", count, dtoList.size());
		
	}
}
