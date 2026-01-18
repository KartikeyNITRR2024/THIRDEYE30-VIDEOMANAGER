package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

@Data
public class VideoSettingDto {
    private Long settingId;
    private String settingName;
    private Boolean introPresent;
    private Integer introTime;
    private Boolean mainVideoPresent;
    private Integer mainVideoTime;
    private Boolean outroPresent;
    private Integer outroTime;
    private Boolean adsVideoPresent;
    private Integer adsVideoTime;
    private String backgroundImageUrl;
    private BigDecimal opacityOfBackgroundImage;
    private Boolean soundPresent;
    private Integer fps;
    private Boolean active;
    private LocalDateTime lastlyUsed;
    private Long height;
    private Long width;
    private String sequence;
    private String note;   
}
