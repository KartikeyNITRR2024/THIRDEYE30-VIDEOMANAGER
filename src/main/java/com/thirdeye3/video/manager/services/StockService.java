package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.StockDto;
import java.util.List;

public interface StockService {
    StockDto createStock(Long groupId, StockDto stockDto);
    StockDto updateStock(Long stockId, StockDto stockDto);
    void deleteStock(Long stockId);
    StockDto getStockById(Long stockId);
}