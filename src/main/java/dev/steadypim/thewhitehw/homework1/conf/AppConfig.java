package dev.steadypim.thewhitehw.homework1.conf;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Класс конфигурации для загрузки данных из json и запуска меню
 */
@Configuration
public class AppConfig implements CommandLineRunner {
    private final UtilityStorageService utilityStorageService;
    private final UtilityStorage utilityStorage;
    private final Environment environment;
    public AppConfig(@NonNull UtilityStorageService utilityStorageService,
                     @NonNull Environment environment,
                     @NonNull UtilityStorage utilityStorage) {
        this.utilityStorageService = utilityStorageService;
        this.utilityStorage = utilityStorage;
        this.environment = environment;
    }
    @Override
    public void run(String... args) {
        String dataFilePath = environment.getProperty("data.file.path");
        utilityStorage.updateFilePath(dataFilePath);
        utilityStorageService.run();
    }
}
