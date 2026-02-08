package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "content_areas")
@Data
public class ContentArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String backgroundImage;
    private boolean isBackgroundImagePresent;
    private Double backgroundOpacity;
    private String backgroundColour;

    private boolean isHeaderPresent;
    private String typeOfHeader;
    private Double headerHeightInPercent;
    private Integer headerStartingPosition;

    private boolean isBarRacePresent;
    private String typeOfBarRace;
    private Double barRaceHeight;
    private Integer barRaceStartingPosition;

    private boolean newsAreaPresent;
    private String typeOfNewsArea;
    private Double newsAreaHeight;
    private Integer newsAreaStartingPosition;

    private boolean isAddPresent;
    private String isAddVideoOrImage;
    private String addUrl;
    private Integer addStartingPosition;
    private Double addHeight;

    private boolean active;
    private LocalDateTime lastUsed;
}