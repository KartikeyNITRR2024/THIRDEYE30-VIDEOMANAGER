package com.thirdeye3.video.manager.repositories;

import com.thirdeye3.video.manager.entities.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileMetadata, Long> {
    Optional<FileMetadata> findByS3Key(String s3Key);
}