package br.com.fiap.parkingTech.parkingticket;

import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record ParkingTicketOpenForm(@NotNull Long driverId, @NotNull Long vehicleId, @NotNull ParkingModality parkingModality, int fixedHours) {

    public int fixedHours() {
        return parkingModality.isFixed() ? fixedHours : 1;
    }

    public boolean isFixedModality() {
        return ParkingModality.FIXED.equals(parkingModality);
    }
}