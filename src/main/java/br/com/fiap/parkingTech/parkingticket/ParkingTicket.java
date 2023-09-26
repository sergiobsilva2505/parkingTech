package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.parkingmeter.ParkingMeter;
import br.com.fiap.parkingTech.payment.PaymentType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class ParkingTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ParkingMeter parkingMeter;

    @Enumerated(EnumType.STRING)
    private ParkingModality parkingModality;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private BigDecimal finalPrice;

    public ParkingTicket() {}

    public ParkingTicket(ParkingMeter parkingMeter, ParkingModality parkingModality, PaymentType paymentType, LocalDateTime startTime) {
        this.parkingMeter = parkingMeter;
        this.parkingModality = parkingModality;
        this.paymentType = paymentType;
        this.startTime = startTime;
    }

    public ParkingTicket(ParkingMeter parkingMeter, ParkingModality parkingModality, PaymentType paymentType, LocalDateTime startTime, LocalDateTime endTime, BigDecimal finalPrice) {
        this.parkingMeter = parkingMeter;
        this.parkingModality = parkingModality;
        this.paymentType = paymentType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.finalPrice = finalPrice;
    }

    public Long getId() {
        return id;
    }

    public ParkingMeter getParkingMeter() {
        return parkingMeter;
    }

    public ParkingModality getParkingModality() {
        return parkingModality;
    }

    public boolean isHourlyModality() {
        return ParkingModality.HOURLY.equals(parkingModality);
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void close() {
        this.endTime = LocalDateTime.now();
        int hoursSpent = (int) Math.ceil(startTime.until(endTime, ChronoUnit.MINUTES) / 60.0);
        this.finalPrice = parkingMeter.getPricePerHour().multiply(BigDecimal.valueOf(hoursSpent));
    }
}
