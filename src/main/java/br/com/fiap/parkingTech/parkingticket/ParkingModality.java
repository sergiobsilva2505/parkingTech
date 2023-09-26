package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.payment.PaymentType;

import java.util.Collection;
import java.util.List;

public enum ParkingModality {
    FIXED(List.of(PaymentType.PIX)), HOURLY(List.of(PaymentType.CREDIT_CARD, PaymentType.DEBIT_CARD, PaymentType.PIX));

    private final Collection<PaymentType> acceptedPaymentTypes;

    ParkingModality(Collection<PaymentType> acceptedPaymentTypes) {
        this.acceptedPaymentTypes = acceptedPaymentTypes;
    }

    public boolean isPaymentTypeAccepted(PaymentType paymentType) {
        return acceptedPaymentTypes.contains(paymentType);
    }
}
