package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.FileMetadata;
import com.thirdeye3.video.manager.entities.News;
import com.thirdeye3.video.manager.projections.NewsAudioProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByVideo_Id(UUID videoId);
    
    @Query("SELECT n.newsId AS newsId, n.audioContent AS audioContent " +
            "FROM News n " +
            "WHERE n.video.id = :videoId " +
            "AND n.isSoundPresent = true " +
            "AND n.isSoundCreated = false")
     List<NewsAudioProjection> findPendingAudioByVideoId(@Param("videoId") UUID videoId);
    
    @Modifying 
    @Transactional 
    @Query("UPDATE News n SET n.isSoundCreated = :status, n.file = :file WHERE n.newsId = :newsId")
    int updateSoundStatusAndFile(@Param("newsId") Long newsId, 
                                 @Param("status") Boolean isSoundCreated, 
                                 @Param("file") FileMetadata file);
}
