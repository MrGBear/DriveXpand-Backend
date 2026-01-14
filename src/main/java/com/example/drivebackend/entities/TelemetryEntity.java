package com.example.drivebackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "telemetry")
@Getter
@Setter
@NoArgsConstructor
public class TelemetryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String deviceId;

    @Column(nullable = false)
    private Instant recordedAt;

    @Convert(converter = TelemetryMetricsConverter.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metrics;
}

