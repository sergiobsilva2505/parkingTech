package br.com.fiap.parkingTech.driver;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;
    private final DriverFormValidator driverFormValidator;

    public DriverController(DriverService driverService, DriverFormValidator driverFormValidator) {
        this.driverService = driverService;
        this.driverFormValidator = driverFormValidator;
    }

    @InitBinder("driverForm")
    void initWebDataBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(driverFormValidator);
    }

    @PostMapping
    ResponseEntity<DriverView> save(@Valid @RequestBody DriverForm driverForm) {
        DriverView driverView = driverService.save(driverForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(driverView.id()).toUri();

        return ResponseEntity.created(uri).body(driverView);
    }

    @GetMapping
    ResponseEntity<List<DriverView>> findAll() {
        List<DriverView> driversView = driverService.findAll();

        return ResponseEntity.ok().body(driversView);
    }

    @GetMapping("/{id}")
    ResponseEntity<DriverView> findById(@PathVariable Long id) {
        DriverView driverView = driverService.findById(id);

        return ResponseEntity.ok().body(driverView);
    }

    @PutMapping("/{id}")
    ResponseEntity<DriverView> update(@PathVariable Long id,@Valid @RequestBody DriverForm driverForm) {
        DriverView driversView = driverService.update(id, driverForm);

        return ResponseEntity.ok().body(driversView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        driverService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
