package pe.edu.upc.center.edunova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EdunovaCenterPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdunovaCenterPlatformApplication.class, args);
    }

}
