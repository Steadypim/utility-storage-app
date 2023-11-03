package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.api.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.exception.UtilityRecordNotFoundException;
import dev.steadypim.thewhitehw.homework1.mapper.UtilityStorageMapper;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Mock
    private UtilityStorageMapper mapper;

    @InjectMocks
    private UtilityStorageService service;


    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        service = new UtilityStorageService(repository, mapper);
        Field idCounterField = UtilityStorageService.class.getDeclaredField("idCounter");
        idCounterField.setAccessible(true);
        AtomicInteger idCounter = new AtomicInteger(1);
        idCounterField.set(service, idCounter);
    }

    @Test
    public void testDisplayRecordById_ExistingRecord() {
        //Arrange
        int id = 1;
        UtilityRecord record = UtilityRecord.builder()
                .id(id)
                .name("test")
                .description("test")
                .link("test")
                .build();

        UtilityRecordDTO expectedDTO = new UtilityRecordDTO();
        expectedDTO.setName("test");
        expectedDTO.setDescription("test");
        expectedDTO.setLink("test");

        when(repository.findById(id)).thenReturn(record);
        when(mapper.toDto(record)).thenReturn(expectedDTO);

        //Act
        UtilityRecordDTO resultDTO = service.displayRecordById(id);

        //Assert
        assertNotNull(resultDTO);
        assertEquals(expectedDTO, resultDTO);
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(record);
    }

    @Test
    public void testDisplayRecordById_NonExistingRecord() {
        //Arrange
        int id = 1;


        when(repository.findById(id)).thenReturn(null);

        //Assert
        assertThrows(UtilityRecordNotFoundException.class, () -> service.displayRecordById(id));

        verify(repository, times(1)).findById(id);
        verify(mapper, never()).toDto(any(UtilityRecord.class));
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

        // Mock repository behavior
        when(repository.findAllByNameCaseInsensitive(eq(name), eq(pageable))).thenReturn(new PageImpl<>(records));

        List<UtilityRecordDTO> dtos = new ArrayList<>();
        dtos.add(new UtilityRecordDTO("John Doe", "test", "test"));

        // Mock mapper behavior
        when(mapper.toDtoList(records)).thenReturn(dtos);

        // Act
        Page<UtilityRecordDTO> result = service.displayRecordsByName(name, page, size);

        // Assert
        assertEquals(dtos, result.getContent());
        assertEquals(pageable, result.getPageable());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testCreateRecord() {
        //Arrange
        UtilityRecordDTO dto = new UtilityRecordDTO();
        dto.setName("test");
        dto.setDescription("test");
        dto.setLink("test");

        UtilityRecord record = new UtilityRecord();
        record.setName("test");
        record.setDescription("test");
        record.setLink("test");

        when(mapper.toEntity(dto)).thenReturn(record);
        when(repository.create(record)).thenReturn(record);
        when(mapper.toDto(record)).thenReturn(dto);

        //Act
        UtilityRecordDTO resultDTO = service.createRecord(dto);

        //Assert
        assertNotNull(resultDTO);
        assertEquals(dto, resultDTO);
        verify(mapper, times(1)).toEntity(dto);
        verify(repository, times(1)).create(record);
        verify(mapper, times(1)).toDto(record);
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
        UtilityRecordDTO dto = new UtilityRecordDTO();
        dto.setName("test");
        dto.setDescription("test");
        dto.setLink("test");

        UtilityRecord record = new UtilityRecord();
        record.setId(id);
        record.setName("test");
        record.setDescription("test");
        record.setLink("test");

        when(mapper.toEntity(dto)).thenReturn(record);

        //Act
        service.updateRecordById(dto, id);

        //Assert
        verify(repository, times(1)).update(record, id);
        verify(mapper, times(1)).toEntity(dto);
    }
}
