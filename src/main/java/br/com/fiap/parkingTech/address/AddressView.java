package br.com.fiap.parkingTech.address;

import br.com.fiap.parkingTech.driver.Driver;
import br.com.fiap.parkingTech.driver.DriverView;


public record AddressView(Long id, String street, String number, String neighborhood, String city, String state, DriverView driver) {

    public AddressView(Address address, Driver driver) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), new DriverView(driver));
    }
}
