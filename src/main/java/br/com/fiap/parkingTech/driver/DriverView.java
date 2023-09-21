package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.AddressView;

public record DriverView(Long id, String name, String email, String mobileNumber, AddressView address) {
}
