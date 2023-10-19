package br.com.fiap.parkingTech.vehicle;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final NewVehicleFormValidator newVehicleFormValidator;
    private final UpdateVehicleFormValidator updateVehicleFormValidator;

    public VehicleController(VehicleService vehicleService, NewVehicleFormValidator newVehicleFormValidator, UpdateVehicleFormValidator updateVehicleFormValidator) {
        this.vehicleService = vehicleService;
        this.newVehicleFormValidator = newVehicleFormValidator;
        this.updateVehicleFormValidator = updateVehicleFormValidator;
    }

    @InitBinder("newVehicleForm")
    void initBinderNewVehicleForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newVehicleFormValidator);
    }

    @InitBinder("updateVehicleForm")
    void initBinderUpdateVehicleForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(updateVehicleFormValidator);
    }

    @PostMapping
    ResponseEntity<VehicleView> create(@Valid @RequestBody NewVehicleForm newVehicleForm) {
        VehicleView vehicleView = vehicleService.save(newVehicleForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicleView.id()).toUri();

        return ResponseEntity.created(uri).body(vehicleView);
    }

    @GetMapping
    ResponseEntity<List<VehicleView>> findAll() {
        List<VehicleView> vehicleView = vehicleService.findAll();

        return ResponseEntity.ok(vehicleView);
    }

    @GetMapping("/{id}")
    ResponseEntity<VehicleView> findById(@PathVariable Long id) {
        VehicleView vehicleView = vehicleService.findById(id);

        return ResponseEntity.ok(vehicleView);
    }

    @PutMapping("/{id}")
    ResponseEntity<VehicleView> update(@PathVariable Long id, @Valid @RequestBody UpdateVehicleForm updateVehicleForm) {
        VehicleView vehicleView = vehicleService.update(id, updateVehicleForm);

        return ResponseEntity.ok(vehicleView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
