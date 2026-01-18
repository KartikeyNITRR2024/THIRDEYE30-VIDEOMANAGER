package com.thirdeye3.video.manager.controllers;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.StockDto;
import com.thirdeye3.video.manager.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vm")
public class StockController {

	@Autowired
    private StockService stockService;

    @PostMapping("/groups/{groupId}/stocks")
    public Response<StockDto> addStockToGroup(@PathVariable Long groupId, @RequestBody StockDto stockDto) {
        return new Response<>(true, 0, null, stockService.createStock(groupId, stockDto));
    }

    @GetMapping("/stocks/{id}")
    public Response<StockDto> getStockById(@PathVariable Long id) {
        return new Response<>(true, 0, null, stockService.getStockById(id));
    }

    @PutMapping("/stocks/{id}")
    public Response<StockDto> updateStock(@PathVariable Long id, @RequestBody StockDto stockDto) {
        return new Response<>(true, 0, null, stockService.updateStock(id, stockDto));
    }

    @DeleteMapping("/stocks/{id}")
    public Response<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return new Response<>(true, 0, null, null);
    }
}