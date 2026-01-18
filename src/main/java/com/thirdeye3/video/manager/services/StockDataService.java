package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.StockDataDto;
import java.util.List;
import java.util.UUID;

public interface StockDataService {
    StockDataDto createStockData(StockDataDto dto);
    StockDataDto getStockDataById(Long id);
    List<StockDataDto> getAllStockData();
    StockDataDto updateStockData(Long id, StockDataDto dto);
    void deleteStockData(Long id);
    List<StockDataDto> getStockDataByVideoId(UUID videoId);
    List<StockDataDto> getStockDataByStockId(Long stockId);
	void createStocksData(List<StockDataDto> dtoList);
}
