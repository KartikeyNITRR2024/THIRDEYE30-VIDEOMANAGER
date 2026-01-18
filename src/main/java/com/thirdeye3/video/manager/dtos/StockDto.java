package com.thirdeye3.video.manager.dtos;

import lombok.Data;

@Data
public class StockDto {
    private Long id;
    private String marketCode;
    private String stockName;
    private Boolean active;
}
