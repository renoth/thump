package de.ninjo.thump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThumpApplication {
    public static void main(String[] args) {
        new SpringApplication(ThumpApplication.class).run(args);
    }
}
