package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.Ending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndingRepository extends JpaRepository<Ending, Long> {
}
