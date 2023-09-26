package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.driver.Driver;
import br.com.fiap.parkingTech.driver.DriverRepository;
import br.com.fiap.parkingTech.payment.PaymentType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.*;

@Component
public class ParkingTicketValidator implements Validator {

    private final DriverRepository driverRepository;

    public ParkingTicketValidator(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ParkingTicketOpenForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ParkingTicketOpenForm parkingTicketOpenForm = (ParkingTicketOpenForm) target;
        Optional<Driver> possibleDriver = driverRepository.findById(parkingTicketOpenForm.driverId());

        if (parkingTicketOpenForm.isFixedModality() && parkingTicketOpenForm.fixedHours() <= 0) {
            errors.rejectValue("fixedHours", "", "Para a modalidade de estacionamento fixo a quantidade de horas deve ser maior que zero");
        }

        if (possibleDriver.isEmpty()) {
            errors.rejectValue("driverId", "", "Motorista não encontrado");
            return;
        }

        Driver driver = possibleDriver.get();

        if (!parkingTicketOpenForm.parkingModality().isPaymentTypeAccepted(driver.getPreferredPayment())) {
            errors.rejectValue("parkingModality", "", "Tipo de pagamento não aceito para a modalidade de estacionamento");
        }
    }

}
