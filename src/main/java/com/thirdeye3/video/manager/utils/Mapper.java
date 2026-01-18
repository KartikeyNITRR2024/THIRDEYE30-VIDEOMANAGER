package com.thirdeye3.video.manager.utils;

import java.util.stream.Collectors;
import com.thirdeye3.video.manager.dtos.AdvertainmentDto;
import com.thirdeye3.video.manager.dtos.EndingDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.IntroVideoDto;
import com.thirdeye3.video.manager.dtos.NewsDto;
import com.thirdeye3.video.manager.dtos.OutroVideoDto;
import com.thirdeye3.video.manager.dtos.StockDataDto;
import com.thirdeye3.video.manager.dtos.StockDto;
import com.thirdeye3.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.dtos.VideoSettingDto;
import com.thirdeye3.video.manager.entities.Advertainment;
import com.thirdeye3.video.manager.entities.Ending;
import com.thirdeye3.video.manager.entities.Group;
import com.thirdeye3.video.manager.entities.IntroVideo;
import com.thirdeye3.video.manager.entities.News;
import com.thirdeye3.video.manager.entities.OutroVideo;
import com.thirdeye3.video.manager.entities.Stock;
import com.thirdeye3.video.manager.entities.StockData;
import com.thirdeye3.video.manager.entities.Video;
import com.thirdeye3.video.manager.entities.VideoDetails;
import com.thirdeye3.video.manager.entities.VideoSetting;

public class Mapper {
	
	public GroupDto mapToDTO(Group entity) {
		if (entity == null) {
	        return null;
	    }
		GroupDto dto = new GroupDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setActive(entity.getActive());
		dto.setStocks(entity.getStocks().stream().map(this::mapStockToDTO).collect(Collectors.toList()));
		return dto;
	}

	public StockDto mapStockToDTO(Stock entity) {
		StockDto dto = new StockDto();
		dto.setId(entity.getId());
		dto.setMarketCode(entity.getMarketCode());
		dto.setStockName(entity.getStockName());
		dto.setActive(entity.getActive());
		return dto;
	}
	
	public StockDto mapToDTO(Stock entity) {
		if (entity == null) {
	        return null;
	    }
        StockDto dto = new StockDto();
        dto.setId(entity.getId());
        dto.setMarketCode(entity.getMarketCode());
        dto.setStockName(entity.getStockName());
        dto.setActive(entity.getActive());
        return dto;
    }
	
	public VideoDto mapToDto(Video entity) {
		if (entity == null) {
	        return null;
	    }
        VideoDto dto = new VideoDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDateTime(entity.getCreatedDateTime());
        dto.setCurrentState(entity.getCurrentState());
        dto.setVideoDate(entity.getVideoDate());
        dto.setNote(entity.getNote());
        dto.setCompleted(entity.getCompleted());
        if (entity.getGroup() != null) {
        	dto.setGroupId(entity.getGroup());
        }
        return dto;
    }
	
	public VideoSetting mapDtoToEntity(VideoSettingDto dto) {
		if (dto == null) {
	        return null;
	    }
		VideoSetting entity = new VideoSetting();
        entity.setSettingName(dto.getSettingName());
        entity.setIntroPresent(dto.getIntroPresent());
        entity.setIntroTime(dto.getIntroTime());
        entity.setMainVideoPresent(dto.getMainVideoPresent());
        entity.setMainVideoTime(dto.getMainVideoTime());
        entity.setOutroPresent(dto.getOutroPresent());
        entity.setOutroTime(dto.getOutroTime());
        entity.setAdsVideoPresent(dto.getAdsVideoPresent());
        entity.setAdsVideoTime(dto.getAdsVideoTime());
        entity.setBackgroundImageUrl(dto.getBackgroundImageUrl());
        entity.setOpacityOfBackgroundImage(dto.getOpacityOfBackgroundImage());
        entity.setSoundPresent(dto.getSoundPresent());
        entity.setFps(dto.getFps());
        entity.setActive(dto.getActive());
        entity.setLastlyUsed(dto.getLastlyUsed());
        entity.setHeight(dto.getHeight());
        entity.setWidth(dto.getWidth());
        entity.setSequence(dto.getSequence());
        entity.setNote(dto.getNote());
        return entity;
    }
	
	public VideoSettingDto mapToDto(VideoSetting entity) {
	    if (entity == null) {
	        return null;
	    }
	    VideoSettingDto dto = new VideoSettingDto();
	    dto.setSettingId(entity.getSettingId());
	    dto.setSettingName(entity.getSettingName());
	    dto.setIntroPresent(entity.getIntroPresent());
	    dto.setIntroTime(entity.getIntroTime());
	    dto.setMainVideoPresent(entity.getMainVideoPresent());
	    dto.setMainVideoTime(entity.getMainVideoTime());
	    dto.setOutroPresent(entity.getOutroPresent());
	    dto.setOutroTime(entity.getOutroTime());
	    dto.setAdsVideoPresent(entity.getAdsVideoPresent());
	    dto.setAdsVideoTime(entity.getAdsVideoTime());
	    dto.setBackgroundImageUrl(entity.getBackgroundImageUrl());
	    dto.setOpacityOfBackgroundImage(entity.getOpacityOfBackgroundImage());
	    dto.setSoundPresent(entity.getSoundPresent());
	    dto.setFps(entity.getFps());
	    dto.setActive(entity.getActive());
	    dto.setLastlyUsed(entity.getLastlyUsed());
	    dto.setHeight(entity.getHeight());
	    dto.setWidth(entity.getWidth());
	    dto.setSequence(entity.getSequence());
	    dto.setNote(entity.getNote());
	    return dto;
	}

    public IntroVideoDto mapToDto(IntroVideo entity) {
        if (entity == null) {
            return null;
        }
        IntroVideoDto dto = new IntroVideoDto();
        dto.setIntroId(entity.getIntroId());
        dto.setIntroName(entity.getIntroName());
        dto.setBackgroundImage(entity.getBackgroundImage());
        dto.setBackgroundColor(entity.getBackgroundColor());
        dto.setBackgroundOpacity(entity.getBackgroundOpacity());
        dto.setIsBackgroundImage(entity.getIsBackgroundImage());
        dto.setIsHeaderPresent(entity.getIsHeaderPresent());
        dto.setHeaderFontType(entity.getHeaderFontType());
        dto.setHeaderFontName(entity.getHeaderFontName());
        dto.setHeaderSize(entity.getHeaderSize());
        dto.setHeaderColor(entity.getHeaderColor());
        dto.setIsSubHeaderPresent(entity.getIsSubHeaderPresent());
        dto.setSubHeaderFontType(entity.getSubHeaderFontType());
        dto.setSubHeaderFontName(entity.getSubHeaderFontName());
        dto.setSubHeaderSize(entity.getSubHeaderSize());
        dto.setSubHeaderColor(entity.getSubHeaderColor());
        dto.setIsLinePresent(entity.getIsLinePresent());
        dto.setLineColor(entity.getLineColor());
        dto.setLineWidth(entity.getLineWidth());
        dto.setIsAdPresent(entity.getIsAdPresent());
        dto.setAdImage(entity.getAdImage());
        dto.setAdImageHeight(entity.getAdImageHeight());
        dto.setAdImageWidth(entity.getAdImageWidth());
        dto.setIsAdImage(entity.getIsAdImage());
        dto.setAdTextFontType(entity.getAdTextFontType());
        dto.setAdTextFontName(entity.getAdTextFontName());
        dto.setAdTextSize(entity.getAdTextSize());
        dto.setAdTextColor(entity.getAdTextColor());
        dto.setActive(entity.getActive());
        dto.setLastlyUsed(entity.getLastlyUsed());
        return dto;
    }

    public IntroVideo mapDtoToEntity(IntroVideoDto dto) {
        if (dto == null) {
            return null;
        }
        IntroVideo entity = new IntroVideo();
        if (dto.getIntroId() != null) {
            entity.setIntroId(dto.getIntroId());
        }
        entity.setIntroName(dto.getIntroName());
        entity.setBackgroundImage(dto.getBackgroundImage());
        entity.setBackgroundColor(dto.getBackgroundColor());
        entity.setBackgroundOpacity(dto.getBackgroundOpacity());
        entity.setIsBackgroundImage(dto.getIsBackgroundImage());
        entity.setIsHeaderPresent(dto.getIsHeaderPresent());
        entity.setHeaderFontType(dto.getHeaderFontType());
        entity.setHeaderFontName(dto.getHeaderFontName());
        entity.setHeaderSize(dto.getHeaderSize());
        entity.setHeaderColor(dto.getHeaderColor());
        entity.setIsSubHeaderPresent(dto.getIsSubHeaderPresent());
        entity.setSubHeaderFontType(dto.getSubHeaderFontType());
        entity.setSubHeaderFontName(dto.getSubHeaderFontName());
        entity.setSubHeaderSize(dto.getSubHeaderSize());
        entity.setSubHeaderColor(dto.getSubHeaderColor());
        entity.setIsLinePresent(dto.getIsLinePresent());
        entity.setLineColor(dto.getLineColor());
        entity.setLineWidth(dto.getLineWidth());
        entity.setIsAdPresent(dto.getIsAdPresent());
        entity.setAdImage(dto.getAdImage());
        entity.setAdImageHeight(dto.getAdImageHeight());
        entity.setAdImageWidth(dto.getAdImageWidth());
        entity.setIsAdImage(dto.getIsAdImage());
        entity.setAdTextFontType(dto.getAdTextFontType());
        entity.setAdTextFontName(dto.getAdTextFontName());
        entity.setAdTextSize(dto.getAdTextSize());
        entity.setAdTextColor(dto.getAdTextColor());
        entity.setActive(dto.getActive());
        entity.setLastlyUsed(dto.getLastlyUsed());
        return entity;
    }
    
    public AdvertainmentDto mapToDto(Advertainment entity) {
        if (entity == null) return null;
        AdvertainmentDto dto = new AdvertainmentDto();
        dto.setAdId(entity.getAdId());
        dto.setAdName(entity.getAdName());
        dto.setAdCompanyName(entity.getAdCompanyName());
        dto.setAdInIntro(entity.getAdInIntro());
        dto.setAdInIntroIsImage(entity.getAdInIntroIsImage());
        dto.setAdInIntroImageUrl(entity.getAdInIntroImageUrl());
        dto.setAdInIntroName(entity.getAdInIntroName());
        dto.setAdInContent(entity.getAdInContent());
        dto.setAdInContentIsImage(entity.getAdInContentIsImage());
        dto.setAdInContentUrl(entity.getAdInContentUrl());
        dto.setAdInContentString(entity.getAdInContentString());
        dto.setAdInPage(entity.getAdInPage());
        dto.setAdInPageVideoOrImage(entity.getAdInPageVideoOrImage());
        dto.setAdInPageVideoUrl(entity.getAdInPageVideoUrl());
        dto.setAdInPageImageUrl(entity.getAdInPageImageUrl());
        dto.setAdInPageSoundPresent(entity.getAdInPageSoundPresent());
        dto.setActive(entity.getActive());
        dto.setLastUsed(entity.getLastUsed());
        dto.setNote(entity.getNote());
        return dto;
    }

    public Advertainment mapDtoToEntity(AdvertainmentDto dto) {
        if (dto == null) return null;
        Advertainment entity = new Advertainment();
        if (dto.getAdId() != null) entity.setAdId(dto.getAdId());
        
        entity.setAdName(dto.getAdName());
        entity.setAdCompanyName(dto.getAdCompanyName());
        entity.setAdInIntro(dto.getAdInIntro());
        entity.setAdInIntroIsImage(dto.getAdInIntroIsImage());
        entity.setAdInIntroImageUrl(dto.getAdInIntroImageUrl());
        entity.setAdInIntroName(dto.getAdInIntroName());
        entity.setAdInContent(dto.getAdInContent());
        entity.setAdInContentIsImage(dto.getAdInContentIsImage());
        entity.setAdInContentUrl(dto.getAdInContentUrl());
        entity.setAdInContentString(dto.getAdInContentString());
        entity.setAdInPage(dto.getAdInPage());
        entity.setAdInPageVideoOrImage(dto.getAdInPageVideoOrImage());
        entity.setAdInPageVideoUrl(dto.getAdInPageVideoUrl());
        entity.setAdInPageImageUrl(dto.getAdInPageImageUrl());
        entity.setAdInPageSoundPresent(dto.getAdInPageSoundPresent());
        entity.setActive(dto.getActive());
        entity.setLastUsed(dto.getLastUsed());
        entity.setNote(dto.getNote());
        return entity;
    }
    

    public OutroVideoDto mapToDto(OutroVideo entity) {
        if (entity == null) return null;
        OutroVideoDto dto = new OutroVideoDto();
        dto.setOutroId(entity.getOutroId());
        dto.setOutroName(entity.getOutroName());
        dto.setIsBackgroundImage(entity.getIsBackgroundImage());
        dto.setBackgroundImageUrl(entity.getBackgroundImageUrl());
        dto.setBackgroundImageColor(entity.getBackgroundImageColor());
        dto.setBackgroundImageOpacity(entity.getBackgroundImageOpacity());
        dto.setHeaderFontType(entity.getHeaderFontType());
        dto.setHeaderFontName(entity.getHeaderFontName());
        dto.setHeaderFontColor(entity.getHeaderFontColor());
        dto.setHeaderFontSize(entity.getHeaderFontSize());
        dto.setSubHeaderFontType(entity.getSubHeaderFontType());
        dto.setSubHeaderFontName(entity.getSubHeaderFontName());
        dto.setSubHeaderFontColor(entity.getSubHeaderFontColor());
        dto.setSubHeaderFontSize(entity.getSubHeaderFontSize());
        dto.setActive(entity.getActive());
        dto.setLastUsed(entity.getLastUsed());
        return dto;
    }

    public OutroVideo mapDtoToEntity(OutroVideoDto dto) {
        if (dto == null) return null;
        OutroVideo entity = new OutroVideo();
        if (dto.getOutroId() != null) entity.setOutroId(dto.getOutroId());
        
        entity.setOutroName(dto.getOutroName());
        entity.setIsBackgroundImage(dto.getIsBackgroundImage());
        entity.setBackgroundImageUrl(dto.getBackgroundImageUrl());
        entity.setBackgroundImageColor(dto.getBackgroundImageColor());
        entity.setBackgroundImageOpacity(dto.getBackgroundImageOpacity());
        entity.setHeaderFontType(dto.getHeaderFontType());
        entity.setHeaderFontName(dto.getHeaderFontName());
        entity.setHeaderFontColor(dto.getHeaderFontColor());
        entity.setHeaderFontSize(dto.getHeaderFontSize());
        entity.setSubHeaderFontType(dto.getSubHeaderFontType());
        entity.setSubHeaderFontName(dto.getSubHeaderFontName());
        entity.setSubHeaderFontColor(dto.getSubHeaderFontColor());
        entity.setSubHeaderFontSize(dto.getSubHeaderFontSize());
        entity.setActive(dto.getActive());
        entity.setLastUsed(dto.getLastUsed());
        return entity;
    }

    public NewsDto mapToDto(News entity) {
        if (entity == null) return null;
        NewsDto dto = new NewsDto();
        dto.setNewsId(entity.getNewsId());
        dto.setHeader(entity.getHeader());
        dto.setContent(entity.getContent());
        dto.setIsImagePresent(entity.getIsImagePresent());
        dto.setImageUrl(entity.getImageUrl());
        if (entity.getVideo() != null) {
            dto.setVideoId(entity.getVideo().getId());
        }
        
        return dto;
    }

    public News mapDtoToEntity(NewsDto dto) {
        if (dto == null) return null;
        News entity = new News();
        if (dto.getNewsId() != null) entity.setNewsId(dto.getNewsId());
        
        entity.setHeader(dto.getHeader());
        entity.setContent(dto.getContent());
        entity.setIsImagePresent(dto.getIsImagePresent());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
    
    public StockDataDto mapToDto(StockData entity) {
        if (entity == null) return null;
        StockDataDto dto = new StockDataDto();
        dto.setId(entity.getId());
        dto.setPastData(entity.getPastData());

        if (entity.getVideo() != null) {
            dto.setVideoId(entity.getVideo().getId());
        }
        if (entity.getStock() != null) {
            dto.setStockId(entity.getStock().getId());
        }
        return dto;
    }

    public StockData mapDtoToEntity(StockDataDto dto) {
        if (dto == null) return null;
        StockData entity = new StockData();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setPastData(dto.getPastData());
        return entity;
    }
    
    public VideoDetailsDto mapToDto(VideoDetails entity) {
        if (entity == null) return null;
        VideoDetailsDto dto = new VideoDetailsDto();
        dto.setId(entity.getId());
        dto.setIntroHeader(entity.getIntroHeader());
        dto.setIntroSubHeader(entity.getIntroSubHeader());
        dto.setOutroHeader(entity.getOutroHeader());
        dto.setOutroSubHeader(entity.getOutroSubHeader());
        dto.setActive(entity.getActive());
        dto.setLastlyUsed(entity.getLastlyUsed());

        if (entity.getVideo() != null) {
            dto.setVideoId(entity.getVideo().getId());
        }
        return dto;
    }

    public VideoDetails mapDtoToEntity(VideoDetailsDto dto) {
        if (dto == null) return null;
        VideoDetails entity = new VideoDetails();
        if (dto.getId() != null) entity.setId(dto.getId());

        entity.setIntroHeader(dto.getIntroHeader());
        entity.setIntroSubHeader(dto.getIntroSubHeader());
        entity.setOutroHeader(dto.getOutroHeader());
        entity.setOutroSubHeader(dto.getOutroSubHeader());
        entity.setActive(dto.getActive());
        entity.setLastlyUsed(dto.getLastlyUsed());
        return entity;
    }
    
    public EndingDto mapToDto(Ending entity) {
        if (entity == null) return null;
        EndingDto dto = new EndingDto();
        dto.setId(entity.getId());
        
        if (entity.getVideo() != null) {
            dto.setVideoId(entity.getVideo().getId());
        }
        return dto;
    }
}
