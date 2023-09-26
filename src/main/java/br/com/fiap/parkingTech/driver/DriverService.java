package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.address.Address;
import br.com.fiap.parkingTech.address.AddressRepository;
import br.com.fiap.parkingTech.exception.*;
import br.com.fiap.parkingTech.payment.PreferredPaymentForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final AddressRepository addressRepository;

    public DriverService(DriverRepository driverRepository,
                         AddressRepository addressRepository) {
        this.driverRepository = driverRepository;
        this.addressRepository = addressRepository;
    }

    public DriverView save(NewDriverForm newDriverForm) {
        List<Address> adresses = addressRepository.findAllById(newDriverForm.adressesIds());
        Driver driver = driverRepository.save(newDriverForm.toEntity(adresses));

        return new DriverView(driver);
    }

    public List<DriverView> findAll() {
        List<Driver> drivers = driverRepository.findAll();

        return drivers.stream().map(DriverView::new).toList();
    }

    public DriverView findById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(id)));

        return new DriverView(driver);
    }

    @Transactional
    public DriverView updatePreferredPayment(Long id, PreferredPaymentForm preferredPayment) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(id)));

        driver.setPreferredPayment(preferredPayment.preferredPaymentType());

        return new DriverView(driver);
    }

    @Transactional
    public DriverView update(Long id, UpdateDriverForm updateDriverForm) {
        List<Address> adresses = addressRepository.findAllById(updateDriverForm.adressesIds());
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(id)));

        driver.merge(updateDriverForm, adresses);

        return new DriverView(driver);
    }

    public void delete(Long id) {
        try {
            Driver driver = driverRepository.getReferenceById(id);
            driverRepository.delete(driver);
        } catch (EntityNotFoundException exception) {
            throw new ServiceNotFoundException("Condutor não existe, id: %d".formatted(id));
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("Condutor de integridade da base");
        }
    }
}
