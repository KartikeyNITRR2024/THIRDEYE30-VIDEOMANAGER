package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.VideoDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoDetailsRepository extends JpaRepository<VideoDetails, Long> {

    List<VideoDetails> findAllByOrderByActiveDescLastlyUsedDesc();
    Optional<VideoDetails> findFirstByVideo_Id(UUID videoId);
    Optional<VideoDetails> findFirstByActiveTrue();
    @Modifying
    @Query("UPDATE VideoDetails v SET v.active = false")
    void deactivateAll();
}
