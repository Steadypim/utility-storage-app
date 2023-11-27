package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepository;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.UtilityStorageService;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.UpdateUtilityRecordArgument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilityStorageServiceTest {

    @Mock
    private UtilityStorageRepository repository;

    @InjectMocks
    private UtilityStorageService service;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        service = new UtilityStorageService(repository);
    }

    @Test
    public void testDisplayRecordById_ExistingRecord() {
        //Arrange
        int id = 0;
        UtilityStorage record = UtilityStorage
                .builder()
                .id(id)
                .name("test")
                .description("test")
                .link("test")
                .build();

        UtilityStorage expectedDTO = new UtilityStorage();
        expectedDTO.setName("test");
        expectedDTO.setDescription("test");
        expectedDTO.setLink("test");

        when(repository.findById(id)).thenReturn(record);

        //Act
        UtilityStorage result = service.findRecordById(id);

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
        assertThrows(EntityNotFoundException.class, () -> service.findRecordById(id));

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testDisplayRecordsByName() {
        // Arrange
        String name = "John";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        List<UtilityStorage> records = new ArrayList<>();
        records.add(new UtilityStorage(1, "John Doe", "test", "test"));

        Page<UtilityStorage> expectedPage = new PageImpl<>(records, pageable, 1);

        // Mock repository behavior
        when(repository.findAllByNameIgnoreCase(eq(name), eq(pageable))).thenReturn(expectedPage);

        // Act
        Page<UtilityStorage> result = service.findAllRecordsByName(name, pageable);

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
        when(repository.create(any(UtilityStorage.class))).thenAnswer(invocation -> {
            UtilityStorage createdRecord = invocation.getArgument(0);
            createdRecord.setId(expectedId);
            return createdRecord;
        });
        //Act
        UtilityStorage result = service.createRecord(record);

        //Assert
        assertEquals(expectedId, result.getId());

        ArgumentCaptor<UtilityStorage> recordCaptor = ArgumentCaptor.forClass(UtilityStorage.class);
        verify(repository).create(recordCaptor.capture());

        UtilityStorage capturedRecord = recordCaptor.getValue();
        assertEquals(record.getName(), capturedRecord.getName());
        assertEquals(record.getLink(), capturedRecord.getLink());
        assertEquals(record.getDescription(), capturedRecord.getDescription());

    }

    @Test
    public void testDeleteRecordById_ExistingRecord() {
        //Arrange
        int id = 1;
        UtilityStorage record = new UtilityStorage();
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
        assertThrows(EntityNotFoundException.class, () -> service.deleteRecordById(id));

        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any(UtilityStorage.class));
    }

    @Test
    public void testUpdateRecordById() {
        //Arrange
        int id = 1;

        UtilityStorage record1 = UtilityStorage.builder()
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
