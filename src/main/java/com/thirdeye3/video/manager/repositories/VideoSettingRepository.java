package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.VideoSetting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSettingRepository extends JpaRepository<VideoSetting, Long> {
	@Modifying
    @Query("UPDATE VideoSetting v SET v.active = false")
    void deactivateAll();
	
	List<VideoSetting> findAllByOrderByActiveDescLastlyUsedDesc();
	
	Optional<VideoSetting> findFirstByActiveTrue();
}
