package br.com.fiap.parkintTech.parkingmeter;

import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingMeterController {

    @GetMapping("/parkingmeter")
    public String findAll() {
        return "ParkingMeter";
    }

    @GetMapping("/parkingmeter/{parkingMeterId}")
    public String findById(@PathVariable Long parkingMeterId) {
        return "ParkingMeterById";
    }

    @GetMapping("/parkingmeter/{parkingMeterId}/ticket/report")
    public String getTicketReport(@PathVariable Long parkingMeterId) {
        return "ParkingMeterTicketReport";
    }

    @PostMapping("/parkingmeter")
    public String create() {
        return "CreateParkingMeter";
    }

    @PutMapping("/parkingmeter/{parkingMeterId}")
    public String update(@PathVariable Long parkingMeterId) {
        return "UpdateParkingMeter";
    }

    @DeleteMapping("/parkingmeter/{parkingMeterId}")
    public String delete(@PathVariable Long parkingMeterId) {
        return "DeleteParkingMeter";
    }

}
