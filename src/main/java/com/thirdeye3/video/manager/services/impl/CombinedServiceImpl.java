package com.thirdeye3.video.manager.services.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.EndingDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;
import com.thirdeye3.video.manager.dtos.GroupDateDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.entities.News;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3.video.manager.projections.NewsAudioProjection;
import com.thirdeye3.video.manager.services.AdvertainmentService;
import com.thirdeye3.video.manager.services.CombinedService;
import com.thirdeye3.video.manager.services.EndingService;
import com.thirdeye3.video.manager.services.FileService;
import com.thirdeye3.video.manager.services.GroupService;
import com.thirdeye3.video.manager.services.IntroVideoService;
import com.thirdeye3.video.manager.services.NewsService;
import com.thirdeye3.video.manager.services.OutroVideoService;
import com.thirdeye3.video.manager.services.StockDataService;
import com.thirdeye3.video.manager.services.VideoDetailsService;
import com.thirdeye3.video.manager.services.VideoService;
import com.thirdeye3.video.manager.services.VideoSettingService;

@Service
public class CombinedServiceImpl implements CombinedService {
	
	@Autowired
    @Lazy
    private CombinedService self;

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private VideoSettingService videoSettingService;
	
	@Autowired
	private AdvertainmentService advertainmentService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private IntroVideoService introVideoService;
	
	@Autowired
	private NewsService newsService; 
	
	@Autowired
	private OutroVideoService outroVideoService;
	
	@Autowired
	private StockDataService stockDataService;
	
	@Autowired
	private VideoDetailsService videoDetailsService;
	
	@Autowired
	private EndingService endingService;
	
	@Autowired
	private FileService fileService;
	
	private static final Logger logger = LoggerFactory.getLogger(CombinedServiceImpl.class);
	
	@Override
    @Cacheable(value = "combineddata", key = "#uuid")
	public CombinedDto getCombinedDto(UUID uuid)
	{
		CombinedDto combinedDto = new CombinedDto();
		logger.info("Getting video data");
		combinedDto.setVideoDto(videoService.getVideoById(uuid));
		
		logger.info("Getting video setting data");
		combinedDto.setVideoSettingDto(videoSettingService.getActiveVideoSetting());
		
		logger.info("Getting video details data");
		combinedDto.setVideoDetailsDto(videoDetailsService.getVideoDetailsByVideoId(uuid));
		
		logger.info("Getting news data");
		combinedDto.setNewsDtoList(newsService.getNewsByVideoId(uuid));
		
		if(combinedDto.getVideoDto().getIsGroupPresent())
		{
			logger.info("Getting group data");
			combinedDto.setGroupDto(groupService.getGroupById(combinedDto.getVideoDto().getGroupId()));
		}
		else
		{
			logger.info("Skipping group data");
		}
		if(combinedDto.getVideoDto().getIsAdPresent())
		{
			combinedDto.setAdvertainmentDto(advertainmentService.getAdvertainmentById(combinedDto.getVideoDto().getAdId()));
		}
		else
		{
			logger.info("Skipping ads data");
		}
		if(combinedDto.getVideoSettingDto().getIntroPresent())
		{
			logger.info("Getting video intro data");
			combinedDto.setIntroVideoDto(introVideoService.getActiveIntroVideo());
		}
		else
		{
			logger.info("Skipping intro data");
		}
		if(combinedDto.getVideoSettingDto().getOutroPresent())
		{
			logger.info("Getting outro data");
			combinedDto.setOutroVideoDto(outroVideoService.getActiveOutroVideo());
		}
		else
		{
			logger.info("Skipping outro data");
		}
		logger.info("Returning all data");
		return combinedDto;
	}
	
	@Override
	public CombinedDto getVideoandActiveResources() {
		EndingDto endingDto = endingService.getEnding();
		UUID uuid = endingDto.getVideoId();
		if(uuid == null)
		{
			throw new ResourceNotFoundException("Uuid not found");
		}
		CombinedDto combinedDto = self.getCombinedDto(uuid);
		if(combinedDto.getVideoDto().getIsGroupPresent())
		{	
			logger.info("Getting stock data");
			combinedDto.setStockDataDtoList(stockDataService.getStockDataByVideoId(uuid));
		}
		else
		{
			logger.info("Skipping group details data");
		}
		return combinedDto;
	}
	
	@Override
	public GroupDateDto getGroupDetails() {
		EndingDto endingDto = endingService.getEnding();
		UUID uuid = endingDto.getVideoId();
		if(uuid == null)
		{
			throw new ResourceNotFoundException("Uuid not found");
		}
		VideoDto videoDto = videoService.getVideoById(uuid);
		GroupDto groupDto = null;
		if(videoDto.getIsGroupPresent())
		{
			groupDto = groupService.getGroupById(videoDto.getGroupId());
		}
		else
		{
			logger.error("Group details not found with id: {}", uuid);
            throw new ResourceNotFoundException("Group details not found with ID: " + uuid);
		}
		GroupDateDto groupDateDto = new GroupDateDto(groupDto, videoDto.getVideoDate());
		return groupDateDto;
	}

	@Override
	public void updateCompleted(Boolean completed) {
		EndingDto endingDto = endingService.getEnding();
		UUID uuid = endingDto.getVideoId();
		if(uuid == null)
		{
			throw new ResourceNotFoundException("Uuid not found");
		}
		videoService.updateCompleted(uuid, completed);
		
	}

	@Override
	public void updateCurrentState(Integer currentState) {
		EndingDto endingDto = endingService.getEnding();
		UUID uuid = endingDto.getVideoId();
		if(uuid == null)
		{
			throw new ResourceNotFoundException("Uuid not found");
		}
		videoService.updateCurrentState(uuid, currentState);
	}
	
	@Override
	public void createStocksData(List<StockDataDto> dtoList) {
		EndingDto endingDto = endingService.getEnding();
		UUID uuid = endingDto.getVideoId();
		if(uuid == null)
		{
			throw new ResourceNotFoundException("Uuid not found");
		}
		int count = 0;
		for(StockDataDto stockDataDto : dtoList)
		{
			stockDataDto.setVideoId(uuid);
			try {
				stockDataService.createStockData(stockDataDto);
				count++;
			} 
			catch(Exception exception)
			{
				logger.info("Falied to add {} ", stockDataDto);
			}
		}
		logger.info("Updated {} out of {} data", count, dtoList.size());
	}
	
	@Override
	public List<NewsAudioProjection> getNewsForSoundGeneration()
	{
		EndingDto endingDto = endingService.getEnding();
		UUID uuid = endingDto.getVideoId();
		if(uuid == null)
		{
			throw new ResourceNotFoundException("Uuid not found");
		}
		return newsService.pendingNewsToGenerateSound(uuid);
	}
	
    @Override
    @Transactional
    public void addSound(Long newsId, FileUploadDto uploadDto)
    {
    	fileService.uploadFile(uploadDto);
    }

}
