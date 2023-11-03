package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.Address;
import br.com.fiap.parkingTech.payment.PaymentType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String driverLicense;
    private String email;
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    private PaymentType preferredPayment;

    @OneToMany
    private Collection<Address> addresses;

    @Deprecated
    public Driver() {
    }

    public Driver(String name, String email, String driverLicense, String mobileNumber, PaymentType preferredPayment, Collection<Address> addresses) {
        this.name = name;
        this.email = email;
        this.driverLicense = driverLicense;
        this.mobileNumber = mobileNumber;
        this.preferredPayment = preferredPayment;
        this.addresses = addresses;
    }

    public void merge(UpdateDriverForm updateDriverForm, Collection<Address> addresses) {
        this.name = updateDriverForm.name();
        this.email = updateDriverForm.email();
        this.driverLicense = updateDriverForm.driverLicense();
        this.mobileNumber = updateDriverForm.mobileNumber();
        this.preferredPayment = updateDriverForm.preferredPayment();
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public PaymentType getPreferredPayment() {
        return preferredPayment;
    }

    public void setPreferredPayment(PaymentType preferredPayment) {
        this.preferredPayment = preferredPayment;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }
}
