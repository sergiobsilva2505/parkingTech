package br.com.fiap.parkintTech.parkingticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingTicketController {

    private final ParkingTicketService parkingTicketService;

    public ParkingTicketController(ParkingTicketService parkingTicketService) {
        this.parkingTicketService = parkingTicketService;
    }

    @PostMapping("/parkingmeter/{parkingMeterId}/ticket/open")
    public ResponseEntity<ParkingTicketView> open(@PathVariable Long parkingMeterId, @RequestBody ParkingTicketOpenForm parkingTicketOpenForm) {
        parkingTicketService.open(parkingMeterId, parkingTicketOpenForm);
        return null;
    }

    @PostMapping("/parkingmeter/{parkingMeterId}/ticket/{ticketId}/close")
    public ResponseEntity<ParkingTicketView> close(@PathVariable Long parkingMeterId, @PathVariable String ticketId, @RequestBody ParkingTicketCloseForm parkingTicketCloseForm) {
        return null;
    }
}
