package com.thirdeye3.video.manager.dtos;

import lombok.Data;

@Data
public class ContentAreaDto {
    private Long id;
    private String name;
    private String description;
    private String backgroundImage;
    private boolean backgroundImagePresent;
    private Double backgroundOpacity;
    private String backgroundColour;
    private boolean headerPresent;
    private String typeOfHeader;
    private Double headerHeightInPercent;
    private Integer headerStartingPosition;
    private boolean barRacePresent;
    private String typeOfBarRace;
    private Double barRaceHeight;
    private Integer barRaceStartingPosition;
    private boolean newsAreaPresent;
    private String typeOfNewsArea;
    private Double newsAreaHeight;
    private Integer newsAreaStartingPosition;
    private boolean addPresent;
    private String addVideoOrImage;
    private String addUrl;
    private Integer addStartingPosition;
    private Double addHeight;
    private boolean active;
}
