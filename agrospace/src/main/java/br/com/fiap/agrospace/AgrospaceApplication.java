package br.com.fiap.agrospace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AgrospaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrospaceApplication.class, args);
    }
}