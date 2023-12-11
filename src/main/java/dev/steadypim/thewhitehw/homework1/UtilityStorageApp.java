package dev.steadypim.thewhitehw.homework1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class UtilityStorageApp {
    public static void main(String[] args) {
        SpringApplication.run(UtilityStorageApp.class, args);
    }
}