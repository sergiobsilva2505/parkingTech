package br.com.fiap.parkintTech.parkingticket;

import br.com.fiap.parkintTech.parkingmeter.ParkingMeter;
import br.com.fiap.parkintTech.parkingmeter.ParkingMeterRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingTicketService {

    private final ParkingMeterRepository parkingMeterRepository;

    public ParkingTicketService(ParkingMeterRepository parkingMeterRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
    }

    public ParkingTicketView open(Long parkingMeterId, ParkingTicketOpenForm parkingTicketOpenForm) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId).orElseThrow();
        return null;
    }

    public ParkingTicketView close(Long parkingMeterId, String ticketId, ParkingTicketCloseForm parkingTicketCloseForm) {
        return null;
    }
}