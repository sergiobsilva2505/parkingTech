package br.com.fiap.parkingTech.driver;

import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverView save(DriverForm driverForm) {
        return null;
    }
}
