package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.Address;
import jakarta.persistence.*;
import br.com.fiap.parkingTech.parkingticket.ParkingTicket;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ParkingMeter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Address address;

    @NotNull
    private BigDecimal pricePerHour;

    public ParkingMeter() {

    }

    public ParkingMeter(Address address, BigDecimal pricePerHour) {
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }
}
