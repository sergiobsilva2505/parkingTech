package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.Address;
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
                            @NotEmpty
                            Collection<Long> adressesIds) {

    public Driver toEntity(Collection<Address> addresses) {
        return new Driver(name, email, driverLicense, mobileNumber, addresses);
    }
}
