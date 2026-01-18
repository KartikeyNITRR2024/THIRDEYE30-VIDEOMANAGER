package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.EndingDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.services.CombinedService;
import com.thirdeye3.video.manager.services.EndingService;
import com.thirdeye3.video.manager.services.StockDataService;
import com.thirdeye3.video.manager.services.VideoService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vm/ending")
public class EndingController {

    @Autowired
    private EndingService endingService;
    
    @Autowired
    private StockDataService stockDataService;
    
    @Autowired
    private CombinedService combinedService;
    
    @Autowired
    private VideoService videoService;

    @GetMapping
    public Response<EndingDto> getEnding() {
        return new Response<>(true, 0, null, endingService.getEnding());
    }
    
    @GetMapping("/{id}/group")
    public Response<GroupDto> getGroupDetails(@PathVariable UUID id) {
        return new Response<>(true, 0, null, combinedService.getGroupDetails(id));
    }
    
    @GetMapping("/{id}/combined")
    public Response<CombinedDto> getCombinedDetails(@PathVariable UUID id) {
        return new Response<>(true, 0, null, combinedService.getVideoandActiveResources(id));
    }
    
    @PostMapping
    public Response<Void> createStocksData(@RequestBody List<StockDataDto> dtoList) {
    	stockDataService.createStocksData(dtoList);
        return new Response<>(true, 0, null, null);
    }
    
    @PatchMapping("/{id}/completed")
    public Response<VideoDto> updateCompleted(@PathVariable UUID id, @RequestBody VideoDto videoDto) {
        return new Response<>(true, 0, null, videoService.updateCompleted(id, videoDto.getCompleted()));
    }

    @PatchMapping("/{id}/current-state")
    public Response<VideoDto> updateCurrentState(@PathVariable UUID id, @RequestBody VideoDto videoDto) {
        return new Response<>(true, 0, null, videoService.updateCurrentState(id, videoDto.getCurrentState()));
    }
}