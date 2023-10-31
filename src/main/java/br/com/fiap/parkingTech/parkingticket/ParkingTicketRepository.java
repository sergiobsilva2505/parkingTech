package br.com.fiap.parkingTech.parkingticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    List<ParkingTicket> findAllByStatusAndEndTimeBetween(ParkingTicketStatus status, LocalDateTime endTime, LocalDateTime endTime2);

    default List<ParkingTicket> findAllExpiringOnNext5Minutes() {
        return findAllByStatusAndEndTimeBetween(ParkingTicketStatus.OPEN, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));
    }
}