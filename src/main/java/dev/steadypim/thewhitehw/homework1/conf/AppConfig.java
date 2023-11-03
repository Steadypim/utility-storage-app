package dev.steadypim.thewhitehw.homework1.conf;

import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации для запуска меню
 */
@Configuration
public class AppConfig implements CommandLineRunner {
    private final UtilityStorageService utilityStorageService;
    public AppConfig(UtilityStorageService utilityStorageService) {
        this.utilityStorageService = utilityStorageService;
    }

    @Override
    public void run(String... args) {
        utilityStorageService.run();
    }
}
