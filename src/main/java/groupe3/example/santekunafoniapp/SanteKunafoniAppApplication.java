package groupe3.example.santekunafoniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //Active le système de planification automatique
public class SanteKunafoniAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanteKunafoniAppApplication.class, args);
    }

}