package br.com.fiap.parkingTech.driver;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewDriverFormValidator implements Validator {

    private final DriverRepository driverRepository;

    public NewDriverFormValidator(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewDriverForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewDriverForm newDriverForm = (NewDriverForm) target;
        String driverLicense = newDriverForm.driverLicense();
        String email = newDriverForm.email();

        if(driverRepository.existsByDriverLicense(driverLicense)) {
            errors.rejectValue("driverLicense", "driverlicense.already.exists");
        }

        if(driverRepository.existsByEmail(email)) {
            errors.rejectValue("email", "email.already.exists");
        }

    }
}
