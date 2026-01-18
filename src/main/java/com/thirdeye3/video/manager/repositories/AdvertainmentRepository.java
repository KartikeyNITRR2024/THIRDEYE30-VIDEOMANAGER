package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.Advertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertainmentRepository extends JpaRepository<Advertainment, Long> {
    List<Advertainment> findAllByOrderByActiveDescLastUsedDesc();
    Optional<Advertainment> findFirstByActiveTrue();
    @Modifying
    @Query("UPDATE Advertainment a SET a.active = false")
    void deactivateAll();
}
