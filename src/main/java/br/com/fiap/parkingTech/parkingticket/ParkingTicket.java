package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.parkingmeter.ParkingMeter;
import br.com.fiap.parkingTech.payment.PaymentType;
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

    @Enumerated(EnumType.STRING)
    private ParkingTicketStatus status;

    @Enumerated(EnumType.STRING)
    private ParkingModality parkingModality;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private BigDecimal finalPrice;

    public ParkingTicket() {}

    public ParkingTicket(ParkingMeter parkingMeter, ParkingModality parkingModality, PaymentType paymentType, LocalDateTime startTime, LocalDateTime endTime, BigDecimal finalPrice) {
        this.parkingMeter = parkingMeter;
        this.status = ParkingTicketStatus.OPEN;
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

    public void setStatus(ParkingTicketStatus status) {
        this.status = status;
    }

    public ParkingModality getParkingModality() {
        return parkingModality;
    }

    public boolean canAutoClose() {
        return ParkingModality.FIXED.equals(parkingModality);
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

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void close() {
        parkingModality.closeParkingTicket(this);
    }

    public long getTotalHours() {
        return startTime.until(endTime, java.time.temporal.ChronoUnit.HOURS);
    }

    public BigDecimal getPricePerHour() {
        return parkingMeter.getPricePerHour();
    }

    public void extendDuration() {
        this.endTime = endTime.plusHours(1);
    }
}
