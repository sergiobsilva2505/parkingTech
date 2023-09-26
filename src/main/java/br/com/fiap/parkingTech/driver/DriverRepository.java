package br.com.fiap.parkingTech.driver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    boolean existsByDriverLicense(String driverLicense);

    boolean existsByEmail(String email);

    boolean existsByDriverLicenseAndIdNot(String driverLicense, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
