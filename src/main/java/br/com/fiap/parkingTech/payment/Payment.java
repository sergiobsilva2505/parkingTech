package br.com.fiap.parkingTech.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Payment {

    private Long id;
    private LocalDateTime date;
    private BigDecimal price;
}
