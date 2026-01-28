package com.example.drivebackend;

import com.example.drivebackend.dto.TelemetryIngestRequest;
import com.example.drivebackend.services.TelemetryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Profile("dev") // Nur im dev-Profil ausführen
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TelemetryService telemetryService;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream is = new ClassPathResource("testdata.json").getInputStream();
        List<TelemetryIngestRequest> testData = mapper.readValue(is, new TypeReference<>() {});

        for (TelemetryIngestRequest request : testData) {
            telemetryService.ingestTelemetry(request);
        }

        System.out.println("Testdaten geladen: " + testData.size() + " Einträge");
    }
}