package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockDataRepository extends JpaRepository<StockData, Long> {
    List<StockData> findByVideo_Id(UUID videoId);
    List<StockData> findByStock_Id(Long stockId);
}