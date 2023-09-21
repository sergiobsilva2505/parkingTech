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
    private String email;
    private String mobileNumber;

    @OneToOne
    private Address address;

    @Deprecated
    public Driver() {
    }

    public Driver(String name, String email, String mobileNumber, Address address) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Address getAddress() {
        return address;
    }
}
