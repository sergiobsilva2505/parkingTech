package br.com.fiap.parkingTech.parkingticket;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingTicketController {

    private final ParkingTicketService parkingTicketService;
    private final ParkingTicketValidator parkingTicketValidator;

    public ParkingTicketController(ParkingTicketService parkingTicketService, ParkingTicketValidator parkingTicketValidator) {
        this.parkingTicketService = parkingTicketService;
        this.parkingTicketValidator = parkingTicketValidator;
    }

    @InitBinder("parkingTicketOpenForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(parkingTicketValidator);
    }

    @PostMapping("/parkingmeter/{parkingMeterId}/ticket/open")
    public ResponseEntity<ParkingTicketView> open(@PathVariable Long parkingMeterId, @Valid @RequestBody ParkingTicketOpenForm parkingTicketOpenForm) {
        ParkingTicketView ticketView = parkingTicketService.open(parkingMeterId, parkingTicketOpenForm);
        return ResponseEntity.ok(ticketView);
    }

    @PostMapping("/parkingmeter/{parkingMeterId}/ticket/{ticketId}/close")
    public ResponseEntity<ParkingTicketView> close(@PathVariable Long parkingMeterId, @PathVariable Long ticketId) {
        ParkingTicketView ticketView = parkingTicketService.close(parkingMeterId, ticketId);
        return ResponseEntity.ok(ticketView);
    }
}
