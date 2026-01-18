package com.thirdeye3.video.manager.dtos;

import java.util.List;

import lombok.Data;

@Data
public class CombinedDto {
   private VideoDto videoDto;
   private VideoSettingDto videoSettingDto;
   private AdvertainmentDto advertainmentDto;
   private GroupDto groupDto;
   private IntroVideoDto introVideoDto; 
   private List<NewsDto> newsDtoList;
   private OutroVideoDto outroVideoDto;
   private List<StockDataDto> stockDataDtoList;
   private VideoDetailsDto videoDetailsDto;
}
