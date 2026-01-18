package com.thirdeye3.video.manager.services.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3.video.manager.services.AdvertainmentService;
import com.thirdeye3.video.manager.services.CombinedService;
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
	
	private static final Logger logger = LoggerFactory.getLogger(CombinedServiceImpl.class);
	
	@Override
	public CombinedDto getVideoandActiveResources(UUID uuid) {
		CombinedDto combinedDto = new CombinedDto();
		combinedDto.setVideoDto(videoService.getVideoById(uuid));
		combinedDto.setVideoSettingDto(videoSettingService.getActiveVideoSetting());
		combinedDto.setVideoDetailsDto(videoDetailsService.getVideoDetailsByVideoId(uuid));
		combinedDto.setNewsDtoList(newsService.getNewsByVideoId(uuid));
		if(combinedDto.getVideoDto().getIsGroupPresent())
		{
			combinedDto.setGroupDto(groupService.getGroupById(combinedDto.getVideoDto().getGroupId()));
			combinedDto.setStockDataDtoList(stockDataService.getStockDataByVideoId(uuid));
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
			combinedDto.setIntroVideoDto(introVideoService.getActiveIntroVideo());
		}
		else
		{
			logger.info("Skipping intro data");
		}
		if(combinedDto.getVideoSettingDto().getOutroPresent())
		{
			combinedDto.setOutroVideoDto(outroVideoService.getActiveOutroVideo());
		}
		else
		{
			logger.info("Skipping outro data");
		}
		return combinedDto;
	}
	
	@Override
	public GroupDto getGroupDetails(UUID uuid) {
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
		return groupDto;
	}

}
