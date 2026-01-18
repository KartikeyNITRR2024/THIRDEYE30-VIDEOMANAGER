package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_data")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StockData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_data_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Lob
    @Column(name = "past_data", columnDefinition = "TEXT")
    private String pastData;
}