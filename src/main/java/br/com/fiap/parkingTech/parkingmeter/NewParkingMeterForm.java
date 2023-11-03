package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.Address;
import br.com.fiap.parkingTech.parkingticket.ParkingTicket;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record NewParkingMeterForm(@NotNull
                                  Long addressId,
                                  @NotNull
                                  BigDecimal pricePerHour) {

    public ParkingMeter toEntity(Address address) {
        return new ParkingMeter(address, pricePerHour);
    }

}
