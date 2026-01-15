package com.example.drivebackend.repository;

import com.example.drivebackend.entities.TelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelemetrySampleRepository extends JpaRepository<TelemetryEntity, UUID> {

    Optional<TelemetryEntity> findTopByDeviceIdOrderByRecordedAtDesc(String deviceId);

    @Query("SELECT t FROM TelemetryEntity t WHERE t.deviceId = :deviceId AND t.recordedAt >= :since AND t.recordedAt <= :end ORDER BY t.recordedAt ASC")
    List<TelemetryEntity> findAllByDeviceIdInRange(
        @Param("deviceId") String deviceId,
        @Param("since") Instant since,
        @Param("end") Instant end
    );
}

