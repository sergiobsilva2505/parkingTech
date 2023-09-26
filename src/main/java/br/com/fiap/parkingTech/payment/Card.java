package br.com.fiap.parkingTech.payment;

import java.time.LocalDate;

public abstract class Card extends Payment {

    private String number;
    private String cvc;
    private LocalDate validity;
}
