package com.upload.repository;

import com.upload.entity.UploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<UploadEntity, Long> {
}
