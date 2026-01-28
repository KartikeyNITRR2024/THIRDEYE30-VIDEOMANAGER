package com.thirdeye3.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;
import com.thirdeye3.video.manager.dtos.GroupDateDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.entities.News;
import com.thirdeye3.video.manager.projections.NewsAudioProjection;

public interface CombinedService {
	CombinedDto getVideoandActiveResources();
	GroupDateDto getGroupDetails();
	void updateCompleted(Boolean completed);
	void updateCurrentState(Integer currentState);
	void createStocksData(List<StockDataDto> dtoList);
	List<NewsAudioProjection> getNewsForSoundGeneration();
	void addSound(Long newsId, FileUploadDto uploadDto);
}
