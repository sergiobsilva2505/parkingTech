package br.com.fiap.parkingTech.address;

import br.com.fiap.parkingTech.driver.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressForm(@NotBlank
                          String street,
                          @NotBlank
                          @Size(min = 1, max = 4)
                          String number,
                          @NotBlank
                          String neighborhood,
                          @NotBlank
                          String city,
                          @NotBlank
                          String state,
                          @NotNull Long driverId) {

    public Address toEntity(Driver driver) {
        return new Address(street, number, neighborhood, city, state, driver);
    }
}
