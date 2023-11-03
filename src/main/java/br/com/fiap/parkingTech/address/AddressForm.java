package br.com.fiap.parkingTech.address;

import jakarta.validation.constraints.NotBlank;

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
