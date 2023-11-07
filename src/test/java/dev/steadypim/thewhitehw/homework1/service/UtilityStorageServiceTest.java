package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UtilityStorageServiceTest {

    private final PrintStream standardOut = System.out;
    @Mock
    private UtilityStorageRepositoryImpl mockRepository;

    private UtilityStorageService storageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        storageService = new UtilityStorageService(mockRepository);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testDisplayRecordById_existingRecord() {
        // Arrange
        int id = 1;
        UtilityRecord record = UtilityRecord.builder()
                .id(id)
                .name("Test Record")
                .description("Description")
                .link("Link")
                .build();
        when(mockRepository.findByIdOrNull(id)).thenReturn(record);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Simulate user input
        String userInput = String.valueOf(id);
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Act
        storageService.displayRecordById();

        // Assert
        String expectedOutput = "Enter the record id: " + record + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that the repository method was called
        verify(mockRepository, times(1)).findByIdOrNull(id);
    }

    @Test
    public void testDisplayRecordById_nonExistingRecord() {
        // Arrange
        int id = 1;
        when(mockRepository.findByIdOrNull(id)).thenReturn(null);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Simulate user input
        String userInput = String.valueOf(id);
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Act
        storageService.displayRecordById();

        // Assert
        String expectedOutput = "Enter the record id: No such record with id: " + id + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that the repository method was called
        verify(mockRepository, times(1)).findByIdOrNull(id);
    }

    @Test
    public void testDisplayRecordsByName_matchingRecordsFound() {
        // Arrange
        String name = "test";
        List<UtilityRecord> records = new ArrayList<>();
        records.add(UtilityRecord.builder()
                .id(1)
                .name("Test Record 1")
                .description("Description 1")
                .link("Link 1")
                .build());
        records.add(UtilityRecord.builder()
                .id(2)
                .name("Test Record 2")
                .description("Description 2")
                .link("Link 2")
                .build());
        when(mockRepository.findAllByNameCaseInsensitive(name)).thenReturn(records);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Simulate user input
        InputStream inputStream = new ByteArrayInputStream(name.getBytes());
        System.setIn(inputStream);

        // Act
        storageService.displayRecordsByName();

        // Assert
        StringBuilder expectedOutput = new StringBuilder();
        for (UtilityRecord record : records) {
            expectedOutput.append(record.toString()).append(System.lineSeparator());
        }


        assertEquals("Enter the name (case-insensitive): " + expectedOutput, outputStream.toString());

        // Verify that the repository method was called
        verify(mockRepository, times(1)).findAllByNameCaseInsensitive(name);
    }

    @Test
    public void testDisplayRecordsByName_noMatchingRecordsFound() {
        // Arrange
        String name = "test";
        List<UtilityRecord> records = new ArrayList<>();
        when(mockRepository.findAllByNameCaseInsensitive(name)).thenReturn(records);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Simulate user input
        InputStream inputStream = new ByteArrayInputStream(name.getBytes());
        System.setIn(inputStream);

        // Act
        storageService.displayRecordsByName();

        // Assert
        String expectedOutput = "Enter the name (case-insensitive): No matching records found." + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that the repository method was called
        verify(mockRepository, times(1)).findAllByNameCaseInsensitive(name);
    }
}
