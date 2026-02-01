package com.thirdeye3.video.manager.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class StockDto implements Serializable {
    private Long id;
    private String marketCode;
    private String stockName;
    private Boolean active;
}
