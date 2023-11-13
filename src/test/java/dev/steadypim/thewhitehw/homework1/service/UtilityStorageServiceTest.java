package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.exception.UtilityRecordNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepositoryImpl;
import dev.steadypim.thewhitehw.homework1.service.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.argument.UpdateUtilityRecordArgument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilityStorageServiceTest {

    @Mock
    private UtilityStorageRepositoryImpl repository;

    @InjectMocks
    private UtilityStorageService service;


    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        service = new UtilityStorageService(repository);
        Field idCounterField = UtilityStorageService.class.getDeclaredField("idCounter");
        idCounterField.setAccessible(true);
        AtomicInteger idCounter = new AtomicInteger(1);
        idCounterField.set(service, idCounter);
    }

    @Test
    public void testDisplayRecordById_ExistingRecord() {
        //Arrange
        int id = 0;
        UtilityRecord record = UtilityRecord
                .builder()
                .id(id)
                .name("test")
                .description("test")
                .link("test")
                .build();

        UtilityRecord expectedDTO = new UtilityRecord();
        expectedDTO.setName("test");
        expectedDTO.setDescription("test");
        expectedDTO.setLink("test");

        when(repository.findById(id)).thenReturn(record);

        //Act
        UtilityRecord result = service.displayRecordById(id);

        //Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testDisplayRecordById_NonExistingRecord() {
        //Arrange
        int id = 1;


        when(repository.findById(id)).thenReturn(null);

        //Assert
        assertThrows(UtilityRecordNotFoundException.class, () -> service.displayRecordById(id));

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testDisplayRecordsByName() {
        // Arrange
        String name = "John";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        List<UtilityRecord> records = new ArrayList<>();
        records.add(new UtilityRecord(1, "John Doe", "test", "test"));

        Page<UtilityRecord> expectedPage = new PageImpl<>(records, pageable, 1);

        // Mock repository behavior
        when(repository.findAllByNameCaseInsensitive(eq(name), eq(pageable))).thenReturn(expectedPage);

        // Act
        Page<UtilityRecord> result = service.displayRecordsByName(name, pageable);

        // Assert
        assertEquals(records, result.getContent());
        assertEquals(pageable, result.getPageable());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testCreateRecord() {
        //Arrange
        CreateUtilityRecordArgument record = new CreateUtilityRecordArgument();
        record.setName("test");
        record.setDescription("test");
        record.setLink("test");

        int expectedId = 1;
        when(repository.create(any(UtilityRecord.class))).thenAnswer(invocation -> {
            UtilityRecord createdRecord = invocation.getArgument(0);
            createdRecord.setId(expectedId);
            return createdRecord;
        });
        //Act
        UtilityRecord result = service.createRecord(record);

        //Assert
        assertEquals(expectedId, result.getId());

        ArgumentCaptor<UtilityRecord> recordCaptor = ArgumentCaptor.forClass(UtilityRecord.class);
        verify(repository).create(recordCaptor.capture());

        UtilityRecord capturedRecord = recordCaptor.getValue();
        assertEquals(record.getName(), capturedRecord.getName());
        assertEquals(record.getLink(), capturedRecord.getLink());
        assertEquals(record.getDescription(), capturedRecord.getDescription());

    }

    @Test
    public void testDeleteRecordById_ExistingRecord() {
        //Arrange
        int id = 1;
        UtilityRecord record = new UtilityRecord();
        record.setId(id);
        record.setName("test");
        record.setDescription("test");
        record.setLink("test");

        when(repository.findById(id)).thenReturn(record);

        //Act
        service.deleteRecordById(id);

        //Assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(record);
    }

    @Test
    public void testDeleteRecordById_NonExistingRecord() {
        //Arrange
        int id = 1;

        when(repository.findById(id)).thenReturn(null);

        //Assert
        assertThrows(UtilityRecordNotFoundException.class, () -> service.deleteRecordById(id));

        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any(UtilityRecord.class));
    }

    @Test
    public void testUpdateRecordById() {
        //Arrange
        int id = 1;

        UtilityRecord record1 = UtilityRecord.builder()
                .id(id)
                .name("test")
                .description("test")
                .link("test")
                .build();

        UpdateUtilityRecordArgument record2 = new UpdateUtilityRecordArgument();
        record2.setName(record1.getName());
        record2.setDescription(record1.getDescription());
        record2.setLink(record1.getLink());


        //Act
        service.updateRecordById(record2, id);

        //Assert
        verify(repository, times(1)).update(record1, id);
    }
}
