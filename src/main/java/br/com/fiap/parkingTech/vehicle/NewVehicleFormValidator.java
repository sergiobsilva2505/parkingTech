package br.com.fiap.parkingTech.vehicle;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewVehicleFormValidator implements Validator {

    private final VehicleRepository vehicleRepository;

    public NewVehicleFormValidator(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewVehicleForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewVehicleForm newVehicleForm = (NewVehicleForm) target;
        String licensePlate = newVehicleForm.licensePlate();

        if(vehicleRepository.existsByLicensePlate(licensePlate)) {
            errors.rejectValue("licensePlate", "licensePlate.already.exists");
        }
    }
}
