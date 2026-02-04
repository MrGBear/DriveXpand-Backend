package com.example.drivebackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drivebackend.entities.TripEntity;

public interface TripRepository extends JpaRepository<TripEntity, UUID> {
}
