package com.thirdeye3.video.manager.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class IntroVideoDto implements Serializable {
    private Long introId;
    private String introName;
    private String backgroundImage;
    private String backgroundColor;
    private Double backgroundOpacity;
    private Boolean isBackgroundImage;
    private Boolean isHeaderPresent;
    private String headerFontType;
    private String headerFontName;
    private Integer headerSize;
    private String headerColor;
    private Boolean isSubHeaderPresent;
    private String subHeaderFontType;
    private String subHeaderFontName;
    private Integer subHeaderSize;
    private String subHeaderColor;
    private Boolean isLinePresent;
    private String lineColor;
    private Integer lineWidth;
    private Boolean isAdPresent;
    private String adImage;
    private Integer adImageHeight;
    private Integer adImageWidth;
    private Boolean isAdImage;
    private String adTextFontType;
    private String adTextFontName;
    private Integer adTextSize;
    private String adTextColor;
    private Boolean active;
    private LocalDateTime lastlyUsed;
}