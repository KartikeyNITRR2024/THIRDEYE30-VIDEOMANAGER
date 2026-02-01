package com.thirdeye3.video.manager.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OutroVideoDto implements Serializable {
    private Long outroId;
    private String outroName;
    private Boolean isBackgroundImage;
    private String backgroundImageUrl;
    private String backgroundImageColor;
    private Double backgroundImageOpacity;
    private String headerFontType;
    private String headerFontName;
    private String headerFontColor;
    private Integer headerFontSize;
    private String subHeaderFontType;
    private String subHeaderFontName;
    private String subHeaderFontColor;
    private Integer subHeaderFontSize;
    private Boolean active;
    private LocalDateTime lastUsed;
}
