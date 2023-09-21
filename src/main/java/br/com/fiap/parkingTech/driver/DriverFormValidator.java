package br.com.fiap.parkingTech.driver;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DriverFormValidator implements Validator {

    private final DriverRepository driverRepository;

    public DriverFormValidator(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DriverForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DriverForm driverForm = (DriverForm) target;
        String driverLicense = driverForm.driverLicense();
        String email = driverForm.email();

        if(driverRepository.existsByDriverLicense(driverLicense)) {
            errors.rejectValue("driverLicense", "driverlicense.already.exists");
        }

        if(driverRepository.existsByEmail(email)) {
            errors.rejectValue("email", "email.already.exists");
        }

    }
}
