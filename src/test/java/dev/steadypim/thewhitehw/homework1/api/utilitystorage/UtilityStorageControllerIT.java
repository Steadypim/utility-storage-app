package dev.steadypim.thewhitehw.homework1.api.utilitystorage;

import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UpdateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepositoryImpl;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.UtilityStorageService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SoftAssertionsExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilityStorageControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UtilityStorageService service;

    @Autowired
    private UtilityStorageRepositoryImpl repository;

    @BeforeEach
    public void setupData() {
        UtilityStorage record1 = new UtilityStorage(1,"Запись 1", "Описание записи 1", "https://example.com/1");
        UtilityStorage record2 = new UtilityStorage(2, "Запись 1", "Описание записи 1", "https://example.com/2");

        repository.create(record1);
        repository.create(record2);
    }
    @Test
    @Order(1)
    void testFindById(SoftAssertions assertions) {
        // Arrange
        int id = 1;
        // Act
        webTestClient.get()
                .uri("/api/v1/utilityStorage/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UtilityRecordDTO.class)
                .value(record -> {
                    // Assert
                    assertions.assertThat(record.getId()).isEqualTo(id);
                });
    }

    @Test
    @Order(2)
    void testFindAllByName() {
        // Arrange
        String name = "Запись 1";

        // Act and Assert
        webTestClient.get()
                .uri("/api/v1/utilityStorage/name?name={name}", name)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.content[0].name").isEqualTo(name)
                .jsonPath("$.content[0].description").isEqualTo("Описание записи 1")
                .jsonPath("$.content[0].link").isEqualTo("https://example.com/1")
                .jsonPath("$.content[1].name").isEqualTo(name)
                .jsonPath("$.content[1].description").isEqualTo("Описание записи 1")
                .jsonPath("$.content[1].link").isEqualTo("https://example.com/2");
    }

    @Test
    @Order(3)
    void testCreate(SoftAssertions assertions) {
        // Arrange
        CreateUtilityRecordDTO recordToCreate = new CreateUtilityRecordDTO("Заявка 2", "Описание записи 2", "https://example.com/3");

        // Act
        webTestClient.post()
                .uri("/api/v1/utilityStorage/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordToCreate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UtilityRecordDTO.class)
                .value(createdRecord -> {
                    // Assert
                    assertions.assertThat(createdRecord.getName()).isEqualTo(recordToCreate.getName());
                    assertions.assertThat(createdRecord.getDescription()).isEqualTo(recordToCreate.getDescription());
                    assertions.assertThat(createdRecord.getLink()).isEqualTo(recordToCreate.getLink());
                });
    }

    @Test
    @Order(5)
    void testDeleteById() {
        // Arrange
        int id = 3;

        // Act
        webTestClient.delete()
                .uri("/api/v1/utilityStorage/{id}", id)
                .exchange()
                .expectStatus().isOk();

        // Assert
        assertThatThrownBy(() -> service.findRecordById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @Order(4)
    void testUpdateById(SoftAssertions assertions) {
        // Arrange
        int id = 3;
        UpdateUtilityRecordDTO recordToUpdate = new UpdateUtilityRecordDTO("Заявка 4", "Описание записи 4", "https://example.com/4");

        // Act
        webTestClient.put()
                .uri("/api/v1/utilityStorage/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordToUpdate)
                .exchange()
                .expectStatus().isOk();

        // Assert
        UtilityStorage updatedRecord = repository.findById(id);
        assertions.assertThat(updatedRecord.getName()).isEqualTo(recordToUpdate.getName());
        assertions.assertThat(updatedRecord.getDescription()).isEqualTo(recordToUpdate.getDescription());
        assertions.assertThat(updatedRecord.getLink()).isEqualTo(recordToUpdate.getLink());
    }

    @Test
    @Order(5)
    void testCreate_returnBadRequest(){
        // Arrange
        CreateUtilityRecordDTO recordToCreate = new CreateUtilityRecordDTO("", "Описание записи 2", "https://example.com/3");

        // Act & Assert
        webTestClient.post()
                .uri("/api/v1/utilityStorage/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordToCreate)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorMessages").isNotEmpty();
    }

    @Test
    @Order(6)
    void testUpdateById_returnBadRequest(){
        // Arrange
        int id = 3;
        UpdateUtilityRecordDTO recordToUpdate = new UpdateUtilityRecordDTO("", "Описание записи 4", "https://example.com/4");

        // Act & Assert
        webTestClient.put()
                .uri("/api/v1/utilityStorage/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordToUpdate)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorMessages").isNotEmpty();
    }
}
