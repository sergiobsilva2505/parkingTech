package br.com.fiap.parkingTech.notification;

import br.com.fiap.parkingTech.parkingticket.ParkingTicket;
import br.com.fiap.parkingTech.parkingticket.ParkingTicketRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketExpiringNotifyJob {

    private final long ONE_MINUTE = 60 * 1000;
    private final ParkingTicketRepository parkingTicketRepository;

    public TicketExpiringNotifyJob(ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
    }

    @Scheduled(fixedRate = ONE_MINUTE)
    public void scheduleExpiringTicketNotification() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
        List<ParkingTicket> allExpiringOnNext5Minutes = parkingTicketRepository.findAllExpiringOnNext5Minutes();

        allExpiringOnNext5Minutes.forEach(parkingTicket -> {
            // TODO: como notificar? Email?
            System.out.println("Enviando notificação para o ticket: " + parkingTicket.getId());

            if (parkingTicket.canAutoClose()) {
                parkingTicket.close();
            } else {
                parkingTicket.extend();
            }
        });

        parkingTicketRepository.saveAll(allExpiringOnNext5Minutes);
    }

}
