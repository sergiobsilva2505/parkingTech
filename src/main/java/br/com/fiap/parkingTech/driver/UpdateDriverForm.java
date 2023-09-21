package br.com.fiap.parkingTech.driver;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
                               String mobileNumber) {

    public Driver toEntity() {
        return new Driver(name, email, driverLicense, mobileNumber);
    }
}
