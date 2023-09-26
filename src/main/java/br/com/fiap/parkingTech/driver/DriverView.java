package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.AddressView;
import br.com.fiap.parkingTech.payment.PaymentType;

import java.util.Collection;

public record DriverView(Long id, String name, String driverLicense, String email, String mobileNumber, PaymentType preferredPayment, Collection<AddressView> addresses) {

    public DriverView(Driver driver) {
        this(driver.getId(), driver.getName(), driver.getDriverLicense(), driver.getEmail(), driver.getMobileNumber(), driver.getPreferredPayment(), driver.getAddresses().stream().map(AddressView::new).toList());
    }
}
