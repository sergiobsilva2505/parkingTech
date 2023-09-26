package br.com.fiap.parkingTech.driver;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdateDriverFormValidator implements Validator {

    private final DriverRepository driverRepository;

    public UpdateDriverFormValidator(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateDriverForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateDriverForm updateDriverForm = (UpdateDriverForm) target;
        Long id = updateDriverForm.id();
        String driverLicense = updateDriverForm.driverLicense();
        String email = updateDriverForm.email();

        if(driverRepository.existsByDriverLicenseAndIdNot(driverLicense, id)) {
            errors.rejectValue("driverLicense", "driverlicense.already.exists");
        }

        if(driverRepository.existsByEmailAndIdNot(email, id)) {
            errors.rejectValue("email", "email.already.exists");
        }

    }
}
