package br.com.fiap.parkingTech.driver;

import jakarta.validation.constraints.*;

import java.util.Collection;

public record UpdateDriverForm(@NotNull
                               Long id,
                               @NotBlank
                               String name,
                               @NotBlank
                               String driverLicense,
                               @NotBlank
                               @Email
                               String email,
                               @NotBlank
                               String mobileNumber,
                               @NotEmpty
                               Collection<Long> adressesIds) {
}
