package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.EndingDto;
import com.thirdeye3.video.manager.dtos.FileResponseDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;
import com.thirdeye3.video.manager.dtos.GroupDateDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.projections.NewsAudioProjection;
import com.thirdeye3.video.manager.services.CombinedService;
import com.thirdeye3.video.manager.services.EndingService;
import com.thirdeye3.video.manager.services.FileService;
import com.thirdeye3.video.manager.services.StockDataService;
import com.thirdeye3.video.manager.services.VideoService;

import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private FileService fileService;
    
    @GetMapping("/group")
    public Response<GroupDateDto> getGroupDetails() {
        return new Response<>(true, 0, null, combinedService.getGroupDetails());
    }
    
    @GetMapping("/combined")
    public Response<CombinedDto> getCombinedDetails() {
        return new Response<>(true, 0, null, combinedService.getVideoandActiveResources());
    }
    
    @GetMapping("/view/{s3Key}")
    public ResponseEntity<Resource> viewImage(@PathVariable String s3Key) {
        Resource resource = fileService.downloadFile(s3Key);
        
        String contentType = "application/octet-stream";
        try {
            contentType = URLConnection.guessContentTypeFromName(s3Key);
        } catch (Exception ignored) {}

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
    @GetMapping("/news")
    public Response<List<NewsAudioProjection>> getNews() {
        return new Response<>(true, 0, null, combinedService.getNewsForSoundGeneration());
    }
    
    @PostMapping(value = "/news/sound/{newsId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<FileResponseDto> uploadFile(@PathVariable Long newsId, @ModelAttribute FileUploadDto uploadDto) {
        return new Response<>(true, 0, null, fileService.uploadAudioFile(newsId, uploadDto));
    }
    
    
    @PostMapping("/group")
    public Response<Void> createStocksData(@RequestBody List<StockDataDto> dtoList) {
    	combinedService.createStocksData(dtoList);
        return new Response<>(true, 0, null, null);
    }
    
    @PatchMapping("/completed/{isCompleted}")
    public Response<Void> updateCompleted(@PathVariable Integer isCompleted) {
    	Boolean completed = (isCompleted == 1);
    	combinedService.updateCompleted(completed);
        return new Response<>(true, 0, null, null);
    }

    @PatchMapping("/current-state/{currentState}")
    public Response<Void> updateCurrentState(@PathVariable Integer currentState) {
    	combinedService.updateCurrentState(currentState);
        return new Response<>(true, 0, null, null);
    }
}