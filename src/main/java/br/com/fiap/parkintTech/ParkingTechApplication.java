package br.com.fiap.parkintTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ParkingTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingTechApplication.class, args);
    }

}
