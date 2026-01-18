package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long newsId;

    @Column(nullable = false)
    private String header;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_image_present")
    private Boolean isImagePresent;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;
}