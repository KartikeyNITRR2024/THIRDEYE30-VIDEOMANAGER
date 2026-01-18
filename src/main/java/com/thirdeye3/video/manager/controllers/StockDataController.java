package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.services.StockDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vm/stock-data")
public class StockDataController {

    @Autowired
    private StockDataService stockDataService;

    @PostMapping
    public Response<StockDataDto> createStockData(@RequestBody StockDataDto dto) {
        return new Response<>(true, 0, null, stockDataService.createStockData(dto));
    }
    
    @PostMapping("/list")
    public Response<Void> createStocksData(@RequestBody List<StockDataDto> dtoList) {
    	stockDataService.createStocksData(dtoList);
        return new Response<>(true, 0, null, null);
    }

    @GetMapping("/{id}")
    public Response<StockDataDto> getStockDataById(@PathVariable Long id) {
        return new Response<>(true, 0, null, stockDataService.getStockDataById(id));
    }

    @GetMapping
    public Response<List<StockDataDto>> getAllStockData() {
        return new Response<>(true, 0, null, stockDataService.getAllStockData());
    }

    @PutMapping("/{id}")
    public Response<StockDataDto> updateStockData(@PathVariable Long id, @RequestBody StockDataDto dto) {
        return new Response<>(true, 0, null, stockDataService.updateStockData(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteStockData(@PathVariable Long id) {
        stockDataService.deleteStockData(id);
        return new Response<>(true, 0, null, null);
    }

    @GetMapping("/video/{videoId}")
    public Response<List<StockDataDto>> getStockDataByVideoId(@PathVariable UUID videoId) {
        return new Response<>(true, 0, null, stockDataService.getStockDataByVideoId(videoId));
    }

    @GetMapping("/stock/{stockId}")
    public Response<List<StockDataDto>> getStockDataByStockId(@PathVariable Long stockId) {
        return new Response<>(true, 0, null, stockDataService.getStockDataByStockId(stockId));
    }
}