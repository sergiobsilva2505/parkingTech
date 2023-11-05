package br.com.fiap.parkingTech.parkingmeter;

import br.com.fiap.parkingTech.address.Address;
import br.com.fiap.parkingTech.address.AddressRepository;
import br.com.fiap.parkingTech.exception.ObjectNotFoundException;
import br.com.fiap.parkingTech.parkingticket.ParkingTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingMeterService {

    public final ParkingMeterRepository parkingMeterRepository;
    public final AddressRepository addressRepository;
    public final ParkingTicketRepository parkingTicketRepository;
    public final ParkingMeterCache parkingMeterCache;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository, AddressRepository addressRepository, ParkingTicketRepository parkingTicketRepository, ParkingMeterCache parkingMeterCache) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.addressRepository = addressRepository;
        this.parkingTicketRepository = parkingTicketRepository;
        this.parkingMeterCache = parkingMeterCache;
    }

    public ParkingMeterView create(NewParkingMeterForm newParkingMeterForm) {
        Address address = addressRepository.findById(newParkingMeterForm.addressId())
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado, id: %s".formatted(newParkingMeterForm.addressId())));
        ParkingMeter parkingMeter = newParkingMeterForm.toEntity(address);

        return new ParkingMeterView(parkingMeterRepository.save(parkingMeter));
    }

    public List<ParkingMeterView> findAll() {
        List<ParkingMeter> parkingMeters = parkingMeterRepository.findAll();

        return parkingMeters.stream().map(ParkingMeterView::new).toList();
    }

    public ParkingMeterView findById(Long id) {
        ParkingMeter parkingMeter = parkingMeterCache.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Parquimetro não encontrado, id: %s".formatted(id)));

        return new ParkingMeterView(parkingMeter);
    }

    @Transactional
    public ParkingMeterView update(Long parkingMeterId, UpdateParkingMeterForm updateParkingMeterForm) {
        ParkingMeter parkingMeter = parkingMeterCache.findById(parkingMeterId)
                .orElseThrow(() -> new ObjectNotFoundException("Parquimetro não encontrado, id: %s".formatted(parkingMeterId)));
        Address address = addressRepository.findById(updateParkingMeterForm.addressId())
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado, id: %s".formatted(updateParkingMeterForm.addressId())));
        parkingMeter.merge(updateParkingMeterForm, address);

        parkingMeterCache.clearCache(parkingMeterId);

        return new ParkingMeterView(parkingMeter);
    }

    public void delete(Long parkingMeterId) {
        parkingMeterRepository.deleteById(parkingMeterId);

        parkingMeterCache.clearCache(parkingMeterId);
    }
}
