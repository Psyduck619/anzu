package zucc.dorm316.anzu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnzuApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnzuApplication.class, args);
    }

}
