package org.example.dto;

import jakarta.persistence.Column;

import java.util.UUID;

//simplified entity
public record EmissionDTO(UUID id,
                          int year,
                          String scenario,
                          String gasUnits,
                          String nk,
                          double value,
                          String category) {
}
