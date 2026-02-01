package com.thirdeye3.video.manager.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CombinedDto implements Serializable {
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
