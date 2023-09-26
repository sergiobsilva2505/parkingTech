package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.payment.PreferredPaymentForm;
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
    private final NewDriverFormValidator newDriverFormValidator;
    private final UpdateDriverFormValidator updateDriverFormValidator;

    public DriverController(DriverService driverService, NewDriverFormValidator newDriverFormValidator, UpdateDriverFormValidator updateDriverFormValidator) {
        this.driverService = driverService;
        this.newDriverFormValidator = newDriverFormValidator;
        this.updateDriverFormValidator = updateDriverFormValidator;
    }

    @InitBinder("newDriverForm")
    void initBinderNewDriverForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newDriverFormValidator);
    }

    @InitBinder("updateDriverForm")
    void initBinderUpdateDriverForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(updateDriverFormValidator);
    }

    @PostMapping
    ResponseEntity<DriverView> save(@Valid @RequestBody NewDriverForm newDriverForm) {
        DriverView driverView = driverService.save(newDriverForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(driverView.id()).toUri();

        return ResponseEntity.created(uri).body(driverView);
    }

    @PostMapping("/{id}/preferred-payment")
    ResponseEntity<?> updatePreferredPayment(@PathVariable Long id, @Valid @RequestBody PreferredPaymentForm preferredPayment) {
        DriverView driverView = driverService.updatePreferredPayment(id, preferredPayment);

        return ResponseEntity.ok(driverView);
    }

    @GetMapping
    ResponseEntity<List<DriverView>> findAll() {
        List<DriverView> driversView = driverService.findAll();

        return ResponseEntity.ok(driversView);
    }

    @GetMapping("/{id}")
    ResponseEntity<DriverView> findById(@PathVariable Long id) {
        DriverView driverView = driverService.findById(id);

        return ResponseEntity.ok(driverView);
    }

    @PutMapping("/{id}")
    ResponseEntity<DriverView> update(@PathVariable Long id, @Valid @RequestBody UpdateDriverForm updateDriverForm) {
        DriverView driversView = driverService.update(id, updateDriverForm);

        return ResponseEntity.ok(driversView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        driverService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
