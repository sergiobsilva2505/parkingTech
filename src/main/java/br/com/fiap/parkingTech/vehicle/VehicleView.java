package br.com.fiap.parkingTech.vehicle;

public record VehicleView(Long id, String brand, String model, String color, String licensePlate, VehicleType vehicleType) {

    public VehicleView(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getColor(), vehicle.getLicensePlate(), vehicle.getVehicleType());
    }
}
