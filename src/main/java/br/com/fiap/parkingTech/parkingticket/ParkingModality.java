package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public enum ParkingModality {
    FIXED(List.of(PaymentType.PIX)) {
        @Override
        public void closeParkingTicket(ParkingTicket parkingTicket) {
            parkingTicket.setFinalPrice(parkingTicket.calculateFinalPrice());
            parkingTicket.setStatus(ParkingTicketStatus.CLOSED);
        }
    }, HOURLY(List.of(PaymentType.CREDIT_CARD, PaymentType.DEBIT_CARD, PaymentType.PIX)) {
        @Override
        public void closeParkingTicket(ParkingTicket parkingTicket) {
            parkingTicket.setEndTime(LocalDateTime.now());
            parkingTicket.setFinalPrice(parkingTicket.calculateFinalPrice());
            parkingTicket.setStatus(ParkingTicketStatus.CLOSED);
        }
    };

    private final Collection<PaymentType> acceptedPaymentTypes;

    ParkingModality(Collection<PaymentType> acceptedPaymentTypes) {
        this.acceptedPaymentTypes = acceptedPaymentTypes;
    }

    public boolean isFixed() {
        return FIXED.equals(this);
    }

    public boolean isPaymentTypeAccepted(PaymentType paymentType) {
        return acceptedPaymentTypes.contains(paymentType);
    }

    public void closeParkingTicket(ParkingTicket parkingTicket) {
        throw new UnsupportedOperationException("Tipo não suportado");
    }
}
