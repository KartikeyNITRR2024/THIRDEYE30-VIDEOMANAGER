package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "video_details")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class VideoDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "intro_header")
    private String introHeader;

    @Column(name = "intro_subheader")
    private String introSubHeader;

    @Column(name = "outro_header")
    private String outroHeader;

    @Column(name = "outro_subheader")
    private String outroSubHeader;

    private Boolean active;

    @Column(name = "lastly_used")
    private LocalDateTime lastlyUsed;
}