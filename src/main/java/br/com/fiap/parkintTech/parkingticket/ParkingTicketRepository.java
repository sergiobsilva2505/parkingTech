package br.com.fiap.parkintTech.parkingticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
}