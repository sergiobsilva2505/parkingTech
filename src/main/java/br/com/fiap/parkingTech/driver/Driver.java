package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.Address;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "drivers")
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String driverLicense;
    private String email;
    private String mobileNumber;

    @Deprecated
    public Driver() {
    }

    public Driver(String name, String email, String driverLicense, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.driverLicense = driverLicense;
        this.mobileNumber = mobileNumber;
    }

    public void merge(DriverForm driverForm) {
        this.name = driverForm.name();
        this.email = driverForm.email();
        this.driverLicense = driverForm.driverLicense();
        this.mobileNumber = driverForm.mobileNumber();
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

}
