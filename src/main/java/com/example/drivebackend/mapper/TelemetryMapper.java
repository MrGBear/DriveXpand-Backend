package com.example.drivebackend.mapper;

import com.example.drivebackend.dto.TelemetryIngestRequest;
import com.example.drivebackend.dto.TelemetryResponse;
import com.example.drivebackend.entities.TelemetryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TelemetryMapper {

    @Mapping(target = "id", ignore = true)
    TelemetryEntity toEntity(TelemetryIngestRequest request);

    TelemetryResponse toDto(TelemetryEntity sample);
}

