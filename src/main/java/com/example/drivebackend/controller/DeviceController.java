package com.example.drivebackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.drivebackend.entities.DeviceEntity;
import com.example.drivebackend.repository.DeviceRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceRepository deviceRepository;

    @GetMapping
    public List<DeviceEntity> getAllDevices() {
        return deviceRepository.findAll();
    }

    @PutMapping("/{deviceId}/name")
    public ResponseEntity<DeviceEntity> updateDeviceName(@PathVariable String deviceId, @RequestBody String name) {
        return deviceRepository.findById(deviceId)
                .map(device -> {
                    device.setName(name);
                    deviceRepository.save(device);
                    return ResponseEntity.ok(device);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}