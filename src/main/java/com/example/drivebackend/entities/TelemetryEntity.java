package com.example.drivebackend.entities;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telemetry")
@Getter
@Setter
@NoArgsConstructor
public class TelemetryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "deviceId", nullable = false)
    private DeviceEntity device;

    @Column(nullable = false)
    private Instant recordedAt;

    @Convert(converter = TelemetryMetricsConverter.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metrics;
}

