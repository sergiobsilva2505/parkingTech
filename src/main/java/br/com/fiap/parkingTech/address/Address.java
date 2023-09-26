package br.com.fiap.parkingTech.address;

import br.com.fiap.parkingTech.driver.Driver;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String street;
    @NotBlank
    @Size(min = 1)
    private String number;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotNull
    @ManyToOne
    private Driver driver;

    @Deprecated
    public Address() {
    }

    public Address(String street, String number, String neighborhood, String city, String state, Driver driver) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.driver = driver;
    }

    public void merge(AddressForm addressForm) {
        this.street = addressForm.street();
        this.number = addressForm.number();
        this.neighborhood = addressForm.neighborhood();
        this.city = addressForm.city();
        this.state = addressForm.state();
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Driver getDriver() {
        return driver;
    }
}
