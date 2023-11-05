package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public record ParkingTicketView(Long id, Long parkingMeterId, ParkingModality parkingModality, PaymentType paymentType, LocalDateTime startTime, LocalDateTime endTime, Long timeSpent, BigDecimal pricePerHour, BigDecimal finalPrice) {

    public ParkingTicketView(ParkingTicket parkingTicket) {
        this(
                parkingTicket.getId(),
                parkingTicket.getParkingMeter().getId(),
                parkingTicket.getParkingModality(),
                parkingTicket.getPaymentType(),
                parkingTicket.getStartTime(),
                parkingTicket.getEndTime(),
                parkingTicket.getStartTime().until(parkingTicket.getEndTime(), ChronoUnit.HOURS),
                parkingTicket.calculateFinalPrice(),
                parkingTicket.getFinalPrice()
        );
    }
}