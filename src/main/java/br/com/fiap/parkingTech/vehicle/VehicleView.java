package br.com.fiap.parkingTech.vehicle;

import br.com.fiap.parkingTech.driver.DriverView;
import com.fasterxml.jackson.annotation.JsonProperty;

public record VehicleView(Long id, String brand, String model, String color, String licensePlate, VehicleType vehicleType, DriverView driver) {

    public VehicleView(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getColor(), vehicle.getLicensePlate(), vehicle.getVehicleType(), new DriverView(vehicle.getDriver()));
    }
}
