package br.com.fiap.parkingTech.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DriverView(Long id, String name, String driverLicense, String email, String mobileNumber) {

    public DriverView(Driver driver) {
        this(driver.getId(), driver.getName(), driver.getDriverLicense(), driver.getEmail(), driver.getMobileNumber());
    }
}
