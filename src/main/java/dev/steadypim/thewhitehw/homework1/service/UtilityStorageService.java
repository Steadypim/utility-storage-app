package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

/**
 * Сервис хранилища
 */
@Service
public class UtilityStorageService {
    private final UtilityStorageRepository storage;

    public UtilityStorageService(@NonNull UtilityStorageRepository storage) {
        this.storage = storage;
    }

    public void displayRecordById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the record id: ");
        int id = scanner.nextInt();
        UtilityRecord record = storage.findByIdOrNull(id);
        if (record != null) {
            System.out.println(record);
        } else {
            System.out.println("No such record with id: " + id);
        }
    }

    public void displayRecordsByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name (case-insensitive): ");
        String name = scanner.nextLine();
        List<UtilityRecord> records = storage.findAllByNameCaseInsensitive(name);
        if (!name.isEmpty()) {
            if (!records.isEmpty()) {
                records.forEach(System.out::println);
            } else {
                System.out.println("No matching records found.");
            }
        } else {
            System.out.println("Name was not provided");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1 - Display a record");
            System.out.println("2 - Search records by name");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Переход на следующую строку, чтобы правильно считать choice

            switch (choice) {
                case 1 -> displayRecordById();
                case 2 -> displayRecordsByName();
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
