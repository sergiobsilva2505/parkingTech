package br.com.fiap.parkingTech.address;

import br.com.fiap.parkingTech.driver.Driver;
import br.com.fiap.parkingTech.driver.DriverRepository;
import br.com.fiap.parkingTech.exception.DatabaseException;
import br.com.fiap.parkingTech.exception.ObjectNotFoundException;
import br.com.fiap.parkingTech.exception.ServiceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final DriverRepository driverRepository;

    public AddressService(AddressRepository addressRepository, DriverRepository driverRepository) {
        this.addressRepository = addressRepository;
        this.driverRepository = driverRepository;
    }

    @Transactional
    public AddressView save(AddressForm addressForm) {
        Driver driver = driverRepository.findById(addressForm.driverId())
                .orElseThrow(() -> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(addressForm.driverId())));
        Address address = addressRepository.save(addressForm.toEntity(driver));

        return new AddressView(address, address.getDriver());
    }

    @Transactional(readOnly = true)
    public List<AddressView> findAll() {
        List<Address> addresses = addressRepository.findAll();

        return addresses.stream().map(address -> new AddressView(address, address.getDriver())).toList();
    }

    @Transactional(readOnly = true)
    public AddressView findById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado, id: %s".formatted(id)));

        return new AddressView(address, address.getDriver());
    }

    @Transactional
    public AddressView update(Long id, AddressForm addressForm) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado, id: %s".formatted(id)));
        address.merge(addressForm);

        return new AddressView(address, address.getDriver());
    }

    public void deleteById(Long id) {
        try {
            Address address = addressRepository.getReferenceById(id);
            addressRepository.delete(address);
        } catch (EntityNotFoundException exception) {
            throw new ServiceNotFoundException("Endereço não existe, id: %d".formatted(id));
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("Violação de integridade da base");
        }
    }
}
