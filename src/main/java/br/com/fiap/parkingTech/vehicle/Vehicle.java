package br.com.fiap.parkingTech.vehicle;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9]{4}", message = "deve corresponder ao padrão (XXX8888)")
    private String licensePlate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, String color, String licensePlate, VehicleType vehicleType) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public void merge(UpdateVehicleForm updateVehicleForm) {
        this.brand = updateVehicleForm.brand();
        this.model = updateVehicleForm.model();
        this.color = updateVehicleForm.color();
        this.licensePlate = updateVehicleForm.licensePlate();
        this.vehicleType = updateVehicleForm.vehicleType();
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
