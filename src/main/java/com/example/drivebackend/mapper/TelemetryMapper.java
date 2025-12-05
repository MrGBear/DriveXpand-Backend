package com.example.drivebackend.mapper;

import com.example.drivebackend.dto.TelemetryIngestRequest;
import com.example.drivebackend.dto.TelemetryResponse;
import com.example.drivebackend.entities.TelemetrySample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TelemetryMapper {

    @Mapping(target = "id", ignore = true)
    TelemetrySample toEntity(TelemetryIngestRequest request);

    TelemetryResponse toDto(TelemetrySample sample);
}

