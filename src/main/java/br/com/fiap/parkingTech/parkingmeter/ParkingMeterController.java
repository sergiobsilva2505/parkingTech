package br.com.fiap.parkingTech.parkingmeter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ParkingMeterController {

    public final ParkingMeterService parkingMeterService;

    public ParkingMeterController(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @PostMapping("/parkingmeter")
    ResponseEntity<ParkingMeterView> create(@Valid @RequestBody NewParkingMeterForm newParkingMeterForm) {
        ParkingMeterView parkingMeterView = parkingMeterService.create(newParkingMeterForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(parkingMeterView.id()).toUri();

        return ResponseEntity.created(uri).body(parkingMeterView);
    }

    @GetMapping("/parkingmeter")
    ResponseEntity<List<ParkingMeterView>> findAll() {
        List<ParkingMeterView> parkingMeterViews = parkingMeterService.findAll();

        return ResponseEntity.ok(parkingMeterViews);
    }

    @GetMapping("/parkingmeter/{id}")
    ResponseEntity<ParkingMeterView> findById(@PathVariable Long id) {
        ParkingMeterView parkingMeterView = parkingMeterService.findById(id);

        return ResponseEntity.ok(parkingMeterView);
    }

//    @GetMapping("/parkingmeter/{parkingMeterId}/ticket/report")
//    ResponseEntity getTicketReport(@PathVariable Long parkingMeterId) {
//        return ReponseEntity.ok();"ParkingMeterTicketReport";
//    }
//
//
//
    @PutMapping("/parkingmeter/{parkingMeterId}")
    ResponseEntity<ParkingMeterView> update(@PathVariable Long parkingMeterId, @Valid @RequestBody UpdateParkingMeterForm updateParkingMeterForm) {
        ParkingMeterView parkingMeterView = parkingMeterService.update(parkingMeterId, updateParkingMeterForm);

        return ResponseEntity.ok(parkingMeterView);
    }

//    @DeleteMapping("/parkingmeter/{parkingMeterId}")
//    ResponseEntity delete(@PathVariable Long parkingMeterId) {
//        return ReponseEntity.ok();"DeleteParkingMeter";
//    }

}
