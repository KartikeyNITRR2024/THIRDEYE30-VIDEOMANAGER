package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.util.List;

@Data
public class GroupDto {
    private Long id;
    private String name;
    private Boolean active;
    private List<StockDto> stocks;
}