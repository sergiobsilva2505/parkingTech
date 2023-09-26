package br.com.fiap.parkingTech.driver;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
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

    public void merge(UpdateDriverForm updateDriverForm) {
        this.name = updateDriverForm.name();
        this.email = updateDriverForm.email();
        this.driverLicense = updateDriverForm.driverLicense();
        this.mobileNumber = updateDriverForm.mobileNumber();
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
