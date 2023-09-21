package br.com.fiap.parkingTech.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    ResponseEntity<DriverView> create(DriverForm driverForm) {
        DriverView driverView = driverService.save(driverForm);

        return null;
    }
}
