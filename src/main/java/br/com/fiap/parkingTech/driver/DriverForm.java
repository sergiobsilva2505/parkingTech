package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.Address;

public record DriverForm(String name, String email, String mobileNumber, Long addressId) {

    public Driver toEntity(Address address) {
        return new Driver(name, email, mobileNumber, address);
    }
}
