package br.com.fiap.parkingTech.parkingmeter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

//    @GetMapping("/parkingmeter")
//    ResponseEntity findAll() {
//        return ReponseEntity.ok();"ParkingMeter";
//    }
//
//    @GetMapping("/parkingmeter/{parkingMeterId}")
//    ResponseEntity findById(@PathVariable Long parkingMeterId) {
//        return ReponseEntity.ok();"ParkingMeterById";
//    }
//
//    @GetMapping("/parkingmeter/{parkingMeterId}/ticket/report")
//    ResponseEntity getTicketReport(@PathVariable Long parkingMeterId) {
//        return ReponseEntity.ok();"ParkingMeterTicketReport";
//    }
//
//
//
//    @PutMapping("/parkingmeter/{parkingMeterId}")
//    ResponseEntity update(@PathVariable Long parkingMeterId) {
//        return ReponseEntity.ok();"UpdateParkingMeter";
//    }
//
//    @DeleteMapping("/parkingmeter/{parkingMeterId}")
//    ResponseEntity delete(@PathVariable Long parkingMeterId) {
//        return ReponseEntity.ok();"DeleteParkingMeter";
//    }

}
