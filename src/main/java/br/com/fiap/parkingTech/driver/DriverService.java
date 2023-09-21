package br.com.fiap.parkingTech.driver;

import br.com.fiap.parkingTech.exception.DatabaseException;
import br.com.fiap.parkingTech.exception.ObjectNotFoundException;
import br.com.fiap.parkingTech.exception.ServiceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Transactional
    public DriverView save(NewDriverForm newDriverForm) {
        Driver driver = driverRepository.save(newDriverForm.toEntity());

        return new DriverView(driver);
    }

    @Transactional(readOnly = true)
    public List<DriverView> findAll() {
        List<Driver> drivers = driverRepository.findAll();

        return drivers.stream().map(DriverView::new).toList();
    }

    @Transactional(readOnly = true)
    public DriverView findById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(id)));

        return new DriverView(driver);
    }

    @Transactional
    public DriverView update(Long id, UpdateDriverForm updateDriverForm) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(id)));
        driver.merge(updateDriverForm);

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
