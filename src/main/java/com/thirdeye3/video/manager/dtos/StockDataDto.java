package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.util.UUID;

@Data
public class StockDataDto {
    private Long id;
    private UUID videoId;
    private Long stockId;
    private String pastData;
}
