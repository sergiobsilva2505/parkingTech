package techchallenge.pqm.parkingticket;

import org.springframework.stereotype.Service;
import techchallenge.pqm.parkingmeter.ParkingMeter;
import techchallenge.pqm.parkingmeter.ParkingMeterRepository;

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
