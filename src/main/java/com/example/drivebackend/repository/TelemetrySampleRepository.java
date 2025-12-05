package com.example.drivebackend.repository;

import com.example.drivebackend.entities.TelemetrySample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TelemetrySampleRepository extends JpaRepository<TelemetrySample, UUID> {

    Optional<TelemetrySample> findTopByDeviceIdOrderByRecordedAtDesc(String deviceId);
}

