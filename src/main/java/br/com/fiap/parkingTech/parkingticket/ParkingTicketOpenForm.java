package br.com.fiap.parkingTech.parkingticket;

import jakarta.validation.constraints.NotNull;

public record ParkingTicketOpenForm(@NotNull Long driverId, @NotNull Long vehicleId, @NotNull ParkingModality parkingModality, int fixedHours) {

    public boolean isFixedModality() {
        return ParkingModality.FIXED.equals(parkingModality);
    }
}