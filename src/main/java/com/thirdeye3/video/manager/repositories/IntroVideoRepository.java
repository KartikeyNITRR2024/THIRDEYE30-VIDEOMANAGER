package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.IntroVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IntroVideoRepository extends JpaRepository<IntroVideo, Long> {
    List<IntroVideo> findAllByOrderByActiveDescLastlyUsedDesc();
    Optional<IntroVideo> findFirstByActiveTrue();
    @Modifying
    @Query("UPDATE IntroVideo i SET i.active = false")
    void deactivateAll();
}