package br.com.fiap.parkingTech.parkingmeter;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ParkingMeterCache {

    private final ParkingMeterRepository parkingMeterRepository;

    public ParkingMeterCache(ParkingMeterRepository parkingMeterRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
    }

    @Cacheable(value = "parkingMeter", key = "#id", unless = "#result == null")
    public Optional<ParkingMeter> findById(Long id) {
        return parkingMeterRepository.findById(id);
    }

    @CacheEvict(value = "parkingMeter", key = "#id")
    public void clearCache(Long id) {

    }
}
