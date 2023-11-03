package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.AddressView;

import java.math.BigDecimal;

public record ParkingMeterView(Long id, AddressView address, BigDecimal pricePerHour) {

    public ParkingMeterView(ParkingMeter parkingMeter) {
        this(parkingMeter.getId(), new AddressView(parkingMeter.getAddress()), parkingMeter.getPricePerHour());
    }
}
