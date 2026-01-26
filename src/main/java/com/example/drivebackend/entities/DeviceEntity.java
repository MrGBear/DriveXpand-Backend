package com.example.drivebackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "device")
@Getter
@Setter
@NoArgsConstructor
public class DeviceEntity {

    @Id
    private String deviceId; // gleiche deviceId wie in TelemetryEntity

    @Column(nullable = false)
    private String name = "Unbenanntes Gerät"; // Default-Wert
}
