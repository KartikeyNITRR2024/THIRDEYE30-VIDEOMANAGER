package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Video {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_date_time", nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "current_state")
    private Integer currentState;

    @Column(name = "video_date")
    private LocalDateTime videoDate;

    @Lob
    @Column(name = "note", columnDefinition = "TEXT") 
    private String note;

    private Boolean completed;

    @Column(name = "group_id")
    private Long group;
    
    @Column(name = "is_group_present")
    private Boolean isGroupPresent;

    @Column(name = "is_ad_present")
    private Boolean isAdPresent;

    @Column(name = "ad_id")
    private Long adId;

    @PrePersist
    protected void onCreate() {
        this.createdDateTime = LocalDateTime.now();
    }
}