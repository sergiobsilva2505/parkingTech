package br.com.fiap.parkingTech.payment;

import jakarta.validation.constraints.NotNull;

public record PreferredPaymentForm(@NotNull PaymentType preferredPaymentType) {
}
