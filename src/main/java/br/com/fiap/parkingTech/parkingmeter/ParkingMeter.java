package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.Address;
import jakarta.persistence.*;
import br.com.fiap.parkingTech.parkingticket.ParkingTicket;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ParkingMeter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "parkingMeter")
    private List<ParkingTicket> parkingTickets;

    private BigDecimal pricePerHour;
    private boolean isOccupied;

}