package com.example.drivebackend.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.drivebackend.entities.TelemetryEntity;

public interface TelemetrySampleRepository extends JpaRepository<TelemetryEntity, UUID> {

    Optional<TelemetryEntity> findTopByDevice_DeviceIdOrderByStartTimeDesc(String deviceId);

    @Query("SELECT t FROM TelemetryEntity t WHERE t.device.deviceId = :deviceId AND t.startTime >= :since AND t.startTime <= :end ORDER BY t.startTime ASC")
    List<TelemetryEntity> findAllByDeviceIdInRange(
        @Param("deviceId") String deviceId,
        @Param("since") Instant since,
        @Param("end") Instant end
    );
}

