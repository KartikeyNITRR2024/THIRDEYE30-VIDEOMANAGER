package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long newsId;

    @Column(nullable = false)
    private String header;

    @Column(name = "content")
    private String content;

    @Column(name = "is_image_present")
    private Boolean isImagePresent;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_sound_present")
    private Boolean isSoundPresent;

    @Column(name = "audio_content")
    private String audioContent;

    @Column(name = "is_sound_created")
    private Boolean isSoundCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileMetadata file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;
}
