package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdvertainmentDto {
    private Long adId;
    private String adName;
    private String adCompanyName;
    private Boolean adInIntro;
    private Boolean adInIntroIsImage;
    private String adInIntroImageUrl;
    private String adInIntroName;
    private Boolean adInContent;
    private Boolean adInContentIsImage;
    private String adInContentUrl;
    private String adInContentString;
    private Boolean adInPage;
    private String adInPageVideoOrImage;
    private String adInPageVideoUrl;
    private String adInPageImageUrl;
    private Boolean adInPageSoundPresent;
    private Boolean active;
    private LocalDateTime lastUsed;
    private String note;
}
