package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.Address;
import br.com.fiap.parkingTech.payment.PaymentType;
import jakarta.validation.constraints.*;

import java.util.Collection;

public record NewDriverForm(@NotBlank
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

    public Driver toEntity(Collection<Address> addresses) {
        return new Driver(name, email, driverLicense, mobileNumber, preferredPayment, addresses);
    }
}
