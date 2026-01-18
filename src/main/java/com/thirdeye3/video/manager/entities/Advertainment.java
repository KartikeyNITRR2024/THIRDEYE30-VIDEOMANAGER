package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "advertainments")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Advertainment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Long adId;

    @Column(name = "ad_name", nullable = false)
    private String adName;

    @Column(name = "ad_company_name")
    private String adCompanyName;

    @Column(name = "ad_in_intro")
    private Boolean adInIntro;

    @Column(name = "ad_in_intro_is_image")
    private Boolean adInIntroIsImage;

    @Column(name = "ad_in_intro_image_url")
    private String adInIntroImageUrl;

    @Column(name = "ad_in_intro_name")
    private String adInIntroName;

    @Column(name = "ad_in_content")
    private Boolean adInContent;

    @Column(name = "ad_in_content_is_image")
    private Boolean adInContentIsImage;

    @Column(name = "ad_in_content_url")
    private String adInContentUrl;

    @Lob
    @Column(name = "ad_in_content_string", columnDefinition = "TEXT")
    private String adInContentString;

    @Column(name = "ad_in_page")
    private Boolean adInPage;

    @Column(name = "ad_in_page_video_or_image")
    private String adInPageVideoOrImage;

    @Column(name = "ad_in_page_video_url")
    private String adInPageVideoUrl;

    @Column(name = "ad_in_page_image_url")
    private String adInPageImageUrl;

    @Column(name = "ad_in_page_sound_present")
    private Boolean adInPageSoundPresent;

    private Boolean active;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    @Lob
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}