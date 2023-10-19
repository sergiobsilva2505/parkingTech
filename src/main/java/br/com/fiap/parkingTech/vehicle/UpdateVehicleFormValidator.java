package br.com.fiap.parkingTech.vehicle;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdateVehicleFormValidator implements Validator {

    private final VehicleRepository vehicleRepository;

    public UpdateVehicleFormValidator(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateVehicleForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateVehicleForm updateVehicleForm = (UpdateVehicleForm) target;
        Long id = updateVehicleForm.id();
        String licensePlate = updateVehicleForm.licensePlate();

        if(vehicleRepository.existsByLicensePlateAndIdNot(licensePlate, id)) {
            errors.rejectValue("licensePlate", "licensePlate.already.exists");
        }
    }
}
