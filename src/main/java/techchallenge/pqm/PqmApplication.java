package techchallenge.pqm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PqmApplication {

    public static void main(String[] args) {
        SpringApplication.run(PqmApplication.class, args);
    }

}
