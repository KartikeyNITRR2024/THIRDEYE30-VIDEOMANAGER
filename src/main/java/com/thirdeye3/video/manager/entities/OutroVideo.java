package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "outro_videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OutroVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outro_id")
    private Long outroId;

    @Column(name = "outro_name", nullable = false)
    private String outroName;

    @Column(name = "is_background_image")
    private Boolean isBackgroundImage;

    @Column(name = "background_image_url")
    private String backgroundImageUrl;

    @Column(name = "background_image_color")
    private String backgroundImageColor;

    @Column(name = "background_image_opacity")
    private Double backgroundImageOpacity;

    @Column(name = "header_font_type")
    private String headerFontType;

    @Column(name = "header_font_name")
    private String headerFontName;

    @Column(name = "header_font_color")
    private String headerFontColor;

    @Column(name = "header_font_size")
    private Integer headerFontSize;

    @Column(name = "subheader_font_type")
    private String subHeaderFontType;

    @Column(name = "subheader_font_name")
    private String subHeaderFontName;

    @Column(name = "subheader_font_color")
    private String subHeaderFontColor;

    @Column(name = "subheader_font_size")
    private Integer subHeaderFontSize;

    private Boolean active;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;
}
