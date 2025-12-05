package com.example.drivebackend.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "telemetry_samples")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TelemetrySample {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String deviceId;

    @Column(nullable = false)
    private Instant recordedAt;

    private Double latitude;

    private Double longitude;

    @Convert(converter = TelemetryMetricsConverter.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metrics;
}

