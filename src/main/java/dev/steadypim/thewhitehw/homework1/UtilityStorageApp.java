package dev.steadypim.thewhitehw.homework1;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepositoryImpl;
import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;

public class UtilityStorageApp {
    public static void main(String[] args) {
        UtilityStorage storage = new UtilityStorage(args[0]);
        UtilityStorageRepositoryImpl storageRepo = new UtilityStorageRepositoryImpl(storage);
        UtilityStorageService service = new UtilityStorageService(storageRepo);
        service.run();
    }
}