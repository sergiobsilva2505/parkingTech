package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParkingTicketView(Long id, Long parkingMeterId, ParkingModality parkingModality, PaymentType paymentType, LocalDateTime startTime, LocalDateTime endTime, BigDecimal finalPrice) {

    public ParkingTicketView(ParkingTicket parkingTicket) {
        this(
                parkingTicket.getId(),
                parkingTicket.getParkingMeter().getId(),
                parkingTicket.getParkingModality(),
                parkingTicket.getPaymentType(),
                parkingTicket.getStartTime(),
                parkingTicket.getEndTime(),
                parkingTicket.getFinalPrice()
        );
    }
}