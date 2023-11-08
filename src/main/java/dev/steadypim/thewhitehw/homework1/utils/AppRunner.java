package dev.steadypim.thewhitehw.homework1.utils;

import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации для запуска меню
 */
@Configuration
public class AppRunner implements CommandLineRunner {
    private final UtilityStorageService utilityStorageService;
    public AppRunner(UtilityStorageService utilityStorageService) {
        this.utilityStorageService = utilityStorageService;
    }

    @Override
    public void run(String... args) {
        utilityStorageService.run();
    }
}
