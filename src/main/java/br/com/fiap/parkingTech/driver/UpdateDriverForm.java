package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.payment.PaymentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
                               @NotNull
                               PaymentType preferredPayment,
                               @NotEmpty
                               Collection<Long> adressesIds) {
}
