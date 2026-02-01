package com.thirdeye3.video.manager.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GroupDto implements Serializable {
    private Long id;
    private String name;
    private Boolean active;
    private List<StockDto> stocks;
}