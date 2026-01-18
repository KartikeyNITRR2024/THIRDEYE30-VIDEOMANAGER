package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "intro_videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class IntroVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intro_id")
    private Long introId;

    @Column(name = "intro_name", nullable = false)
    private String introName;

    @Column(name = "background_image")
    private String backgroundImage;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "background_opacity")
    private Double backgroundOpacity;
    
    @Column(name = "is_background_image")
    private Boolean isBackgroundImage; 
    
    @Column(name = "is_header")
    private Boolean isHeaderPresent;

    @Column(name = "header_font_type")
    private String headerFontType;

    @Column(name = "header_font_name")
    private String headerFontName;

    @Column(name = "header_size")
    private Integer headerSize;

    @Column(name = "header_color")
    private String headerColor;
    
    @Column(name = "is_subheader")
    private Boolean isSubHeaderPresent;

    @Column(name = "subheader_font_type")
    private String subHeaderFontType;

    @Column(name = "subheader_font_name")
    private String subHeaderFontName;

    @Column(name = "subheader_size")
    private Integer subHeaderSize;

    @Column(name = "subheader_color")
    private String subHeaderColor;
    
    @Column(name = "is_line")
    private Boolean isLinePresent;

    @Column(name = "line_color")
    private String lineColor;

    @Column(name = "line_width")
    private Integer lineWidth;
    
    @Column(name = "is_ad_present")
    private Boolean isAdPresent;

    @Column(name = "ad_image")
    private String adImage;

    @Column(name = "ad_image_height")
    private Integer adImageHeight;

    @Column(name = "ad_image_width")
    private Integer adImageWidth;

    @Column(name = "is_ad_image")
    private Boolean isAdImage; 

    @Column(name = "ad_text_font_type")
    private String adTextFontType;

    @Column(name = "ad_text_font_name")
    private String adTextFontName;

    @Column(name = "ad_text_size")
    private Integer adTextSize;

    @Column(name = "ad_text_color")
    private String adTextColor;

    private Boolean active;

    @Column(name = "lastly_used")
    private LocalDateTime lastlyUsed;
}
