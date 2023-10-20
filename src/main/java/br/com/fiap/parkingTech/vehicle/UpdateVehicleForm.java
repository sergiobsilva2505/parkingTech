package br.com.fiap.parkingTech.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateVehicleForm(@NotNull
                                Long id,
                                @NotBlank
                                String brand,
                                @NotBlank
                                String model,
                                @NotBlank
                                String color,
                                @NotBlank
                                @Pattern(regexp = "[A-Z]{3}[0-9]{4}", message = "deve corresponder ao padrão (XXX8888)")
                                String licensePlate,
                                @NotNull
                                VehicleType vehicleType,
                                @NotNull
                                Long driverId) {
}
