package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "video_settings")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class VideoSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private Long settingId;

    @Column(name = "setting_name", nullable = false)
    private String settingName;

    @Column(name = "intro_present")
    private Boolean introPresent;

    @Column(name = "intro_time")
    private Integer introTime;

    @Column(name = "main_video_present")
    private Boolean mainVideoPresent;

    @Column(name = "main_video_time")
    private Integer mainVideoTime;

    @Column(name = "outro_present")
    private Boolean outroPresent;

    @Column(name = "outro_time")
    private Integer outroTime;

    @Column(name = "ads_video_present")
    private Boolean adsVideoPresent;

    @Column(name = "ads_video_time")
    private Integer adsVideoTime;

    @Column(name = "background_image_url")
    private String backgroundImageUrl;

    @Column(name = "opacity_background", precision = 3, scale = 2)
    private BigDecimal opacityOfBackgroundImage;

    @Column(name = "sound_present")
    private Boolean soundPresent;

    private Integer fps;

    private Boolean active;
    
    @Column(name = "lastly_used")
    private LocalDateTime lastlyUsed;
    
    @Column(name = "video_height")
    private Long height;
    
    @Column(name = "video_width")
    private Long width;
    
    @Column(name = "sequence")
    private String sequence;

    @Lob
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
