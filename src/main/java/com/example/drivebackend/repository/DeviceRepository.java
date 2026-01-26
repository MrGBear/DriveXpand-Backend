package com.example.drivebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drivebackend.entities.DeviceEntity;

public interface DeviceRepository extends JpaRepository<DeviceEntity, String> {
}
