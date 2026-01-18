package com.thirdeye3.video.manager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ending")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Ending {

    @Id
    @Column(name = "id")
    private Long id; 
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;
}
