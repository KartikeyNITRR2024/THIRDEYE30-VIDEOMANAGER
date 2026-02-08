package com.thirdeye3.video.manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thirdeye3.video.manager.entities.ContentArea;

@Repository
public interface ContentAreaRepository extends JpaRepository<ContentArea, Long> {;
    Optional<ContentArea> findByActiveTrue();
    @Modifying
    @Query("UPDATE ContentArea c SET c.active = false")
    void deactivateAll();
    
    @Query("select c from ContentArea c where c.active = true")
    Optional<ContentArea> findActive();

}