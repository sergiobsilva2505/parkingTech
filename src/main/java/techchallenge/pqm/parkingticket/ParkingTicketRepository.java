package techchallenge.pqm.parkingticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
}