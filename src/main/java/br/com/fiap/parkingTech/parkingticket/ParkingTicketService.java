package br.com.fiap.parkingTech.parkingticket;

import br.com.fiap.parkingTech.driver.Driver;
import br.com.fiap.parkingTech.driver.DriverRepository;
import br.com.fiap.parkingTech.exception.ObjectNotFoundException;
import br.com.fiap.parkingTech.parkingmeter.ParkingMeter;
import br.com.fiap.parkingTech.parkingmeter.ParkingMeterRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ParkingTicketService {

    private final DriverRepository driverRepository;
    private final ParkingMeterRepository parkingMeterRepository;
    private final ParkingTicketRepository parkingTicketRepository;

    public ParkingTicketService(DriverRepository driverRepository, ParkingMeterRepository parkingMeterRepository,
                                ParkingTicketRepository parkingTicketRepository) {
        this.driverRepository = driverRepository;
        this.parkingMeterRepository = parkingMeterRepository;
        this.parkingTicketRepository = parkingTicketRepository;
    }

    // TODO: 20/10/2023 validar se parquimetro está desocupado (baixa prioridade)
    public ParkingTicketView open(Long parkingMeterId, ParkingTicketOpenForm parkingTicketOpenForm) {
        Driver driver = driverRepository.findById(parkingTicketOpenForm.driverId()).orElseThrow(()-> new ObjectNotFoundException("Condutor não encontrado, id: %d".formatted(parkingTicketOpenForm.driverId())));
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId).orElseThrow(()-> new ObjectNotFoundException("Parquímetro não encontrado, id: %d".formatted(parkingMeterId)));

        LocalDateTime startTime = LocalDateTime.now();
        ParkingTicket parkingTicket;

        LocalDateTime endTime = startTime.plusHours(parkingTicketOpenForm.fixedHours());
        BigDecimal finalPrice = parkingMeter.getPricePerHour().multiply(BigDecimal.valueOf(parkingTicketOpenForm.fixedHours()));

        parkingTicket = new ParkingTicket(parkingMeter, parkingTicketOpenForm.parkingModality(), driver.getPreferredPayment(), startTime, endTime, finalPrice);

        parkingTicketRepository.save(parkingTicket);

        return new ParkingTicketView(parkingTicket);
    }

    public ParkingTicketView close(Long ticketId) {
        ParkingTicket parkingTicket = parkingTicketRepository.findById(ticketId).orElseThrow(()-> new ObjectNotFoundException("Tíquete não encontrado, id: %d".formatted(ticketId)));

        parkingTicket.close();
        parkingTicketRepository.save(parkingTicket);

        return new ParkingTicketView(parkingTicket);
    }
}
