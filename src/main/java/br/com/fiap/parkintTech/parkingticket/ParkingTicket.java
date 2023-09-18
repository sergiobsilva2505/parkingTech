package br.com.fiap.parkintTech.parkingticket;

import br.com.fiap.parkintTech.parkingmeter.ParkingMeter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ParkingTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ParkingMeter parkingMeter;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private BigDecimal finalPrice;

}
