package br.com.fiap.parkingTech.parkingmeter;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {

    @Override
    @Cacheable(value = "parkingMeter", key = "#id", unless = "#result == null")
    Optional<ParkingMeter> findById(Long id);

    @CacheEvict(value = "parkingMeter", key = "#id")
    void clearCache(Long id);
}