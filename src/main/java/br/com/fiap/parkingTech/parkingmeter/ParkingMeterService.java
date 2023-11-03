package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.Address;
import br.com.fiap.parkingTech.address.AddressRepository;
import br.com.fiap.parkingTech.exception.ObjectNotFoundException;
import br.com.fiap.parkingTech.parkingticket.ParkingTicket;
import br.com.fiap.parkingTech.parkingticket.ParkingTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingMeterService {

    public final ParkingMeterRepository parkingMeterRepository;
    public final AddressRepository addressRepository;
    public final ParkingTicketRepository parkingTicketRepository;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository, AddressRepository addressRepository, ParkingTicketRepository parkingTicketRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.addressRepository = addressRepository;
        this.parkingTicketRepository = parkingTicketRepository;
    }

    public ParkingMeterView create(NewParkingMeterForm newParkingMeterForm) {
        Address address = addressRepository.findById(newParkingMeterForm.addressId())
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado, id: %s".formatted(newParkingMeterForm.addressId())));
        ParkingMeter parkingMeter = newParkingMeterForm.toEntity(address);

        return new ParkingMeterView(parkingMeterRepository.save(parkingMeter));
    }
}
