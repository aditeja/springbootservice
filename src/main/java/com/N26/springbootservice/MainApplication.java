package com.N26.springbootservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Main application.
 */
@SpringBootApplication
@EnableJpaRepositories
public class MainApplication {
    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}