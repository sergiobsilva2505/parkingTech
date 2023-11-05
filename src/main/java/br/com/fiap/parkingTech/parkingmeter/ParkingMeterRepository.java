package br.com.fiap.parkingTech.parkingmeter;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {


}