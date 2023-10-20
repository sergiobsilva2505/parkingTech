package br.com.fiap.parkingTech.vehicle;

import br.com.fiap.parkingTech.driver.Driver;
import br.com.fiap.parkingTech.driver.DriverRepository;
import br.com.fiap.parkingTech.exception.DatabaseException;
import br.com.fiap.parkingTech.exception.ObjectNotFoundException;
import br.com.fiap.parkingTech.exception.ServiceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleService(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleView save(NewVehicleForm newVehicleForm) {
        Driver driver = driverRepository.findById(newVehicleForm.driverId()).orElseThrow(() -> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(newVehicleForm.driverId())));
        Vehicle vehicle = vehicleRepository.save(newVehicleForm.toEntity(driver));

        return new VehicleView(vehicle);
    }

    public List<VehicleView> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles.stream().map(VehicleView::new).toList();
    }

    public VehicleView findById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado, id: %s".formatted(id)));

        return new VehicleView(vehicle);
    }

    @Transactional
    public VehicleView update(Long id, UpdateVehicleForm updateVehicleForm) {
        Driver driver = driverRepository.findById(updateVehicleForm.driverId()).orElseThrow(() -> new ObjectNotFoundException("Condutor não encontrado, id: %s".formatted(updateVehicleForm.driverId())));
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado, id: %s".formatted(id)));
        vehicle.merge(updateVehicleForm, driver);

        return new VehicleView(vehicle);
    }

    public void deleteById(Long id) {
        try {
            Vehicle vehicle = vehicleRepository.getReferenceById(id);
            vehicleRepository.delete(vehicle);
        } catch (EntityNotFoundException exception) {
            throw new ServiceNotFoundException("Veículo não existe, id: %d".formatted(id));
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("Violação de integridade da base");
        }
    }
}
