package br.com.fiap.parkingTech.address;

import br.com.fiap.parkingTech.driver.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressForm(@NotBlank
                          String street,
                          @NotBlank
                          String number,
                          @NotBlank
                          String neighborhood,
                          @NotBlank
                          String city,
                          @NotBlank
                          String state) {

    public Address toEntity() {
        return new Address(street, number, neighborhood, city, state);
    }
}
