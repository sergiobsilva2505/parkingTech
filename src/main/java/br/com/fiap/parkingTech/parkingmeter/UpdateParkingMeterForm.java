package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.Address;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateParkingMeterForm(@NotNull Long id, @NotNull Long addressId, @NotNull BigDecimal pricePerHour) {

}
