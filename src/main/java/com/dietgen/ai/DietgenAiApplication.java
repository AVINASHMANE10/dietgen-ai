package com.dietgen.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DietgenAiApplication {
    static {
        // Must run BEFORE Spring Boot builds the DataSource / Hibernate
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
    }
    public static void main(String[] args) {
        SpringApplication.run(DietgenAiApplication.class, args);
    }

}
