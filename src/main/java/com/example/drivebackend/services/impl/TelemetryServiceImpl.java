package com.example.drivebackend.services.impl;

import com.example.drivebackend.dto.TelemetryIngestRequest;
import com.example.drivebackend.dto.TelemetryResponse;
import com.example.drivebackend.entities.TelemetryEntity;
import com.example.drivebackend.mapper.TelemetryMapper;
import com.example.drivebackend.repository.TelemetrySampleRepository;
import com.example.drivebackend.services.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TelemetryServiceImpl implements TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);

    private final TelemetrySampleRepository telemetrySampleRepository;
    private final TelemetryMapper telemetryMapper;

    @Override
    @Transactional
    public TelemetryResponse ingestTelemetry(TelemetryIngestRequest request) {
        TelemetryEntity sample = telemetryMapper.toEntity(request);
        telemetrySampleRepository.save(sample);
        log.debug("Stored telemetry sample for device {}", request.deviceId());
        return telemetryMapper.toDto(sample);
    }

    @Override
    public Optional<TelemetryResponse> fetchLatestTelemetry(String deviceId) {
        return telemetrySampleRepository.findTopByDeviceIdOrderByRecordedAtDesc(deviceId)
                .map(telemetryMapper::toDto);
    }

    @Override
    public List<TelemetryResponse> fetchTelemetryInRange(String deviceId, Instant since, Instant end) {
        List<TelemetryEntity> response = telemetrySampleRepository.findAllByDeviceIdInRange(deviceId, since, end);
        List<TelemetryResponse> dtoResult = response.stream().map(telemetryMapper::toDto).toList();
        return dtoResult;
    }
    
    @Override
    public Map<Instant, List<TelemetryResponse>> fetchTelemetryGroupedByDrive(String deviceId, Instant since, Instant end, int timeBetweenDrivesInSeconds) {
        List<TelemetryEntity> response = telemetrySampleRepository.findAllByDeviceIdInRange(deviceId, since, end);
        List<TelemetryResponse> dtoResponses = response.stream().map(telemetryMapper::toDto).toList();
        Map<Instant, List<TelemetryResponse>> aggregatedDrivesMap = new java.util.HashMap<>();
        
        List<TelemetryResponse> drive = new ArrayList<>();
        for (TelemetryResponse entity : dtoResponses) {
            // Starte einen neuen Fahrt entry, wenn seit dem letzten eintrag 10 min vergangen sind
            if (drive.getLast() != null && drive.getLast().recordedAt().isBefore( entity.recordedAt().minusSeconds(timeBetweenDrivesInSeconds) )){
                // Map(Startzeitpunkt, List der TelemetryResponses der Fahrt)
                aggregatedDrivesMap.put(drive.get(0).recordedAt(), drive);
                drive = new ArrayList<>();
            } else {
                drive.add(entity);
            }
        }

        return aggregatedDrivesMap;
    }
}

