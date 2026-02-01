package com.thirdeye3.video.manager.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class StockDataDto implements Serializable {
    private Long id;
    private UUID videoId;
    private Long stockId;
    private String pastData;
}
