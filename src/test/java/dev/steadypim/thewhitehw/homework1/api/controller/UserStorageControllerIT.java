package dev.steadypim.thewhitehw.homework1.api.controller;

import dev.steadypim.thewhitehw.homework1.api.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.exception.UtilityRecordNotFoundException;
import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class UserStorageControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UtilityStorageService service;

    @Test
    @Order(1)
    void testFindById(SoftAssertions assertions) {
        // Arrange
        int id = 1;
        UtilityRecordDTO expectedRecord = new UtilityRecordDTO(id, "Запись 1", "Описание записи 1", "https://example.com/1");

        // Act
        webTestClient.get()
                .uri("/api/v1/utilityStorage/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UtilityRecordDTO.class)
                .value(record -> {
                    // Assert
                    assertions.assertThat(record)
                            .isEqualToComparingFieldByField(expectedRecord);
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
        CreateUtilityRecordDTO recordToCreate = new CreateUtilityRecordDTO("Заявка 4", "Описание записи 2", "https://example.com/2");

        UtilityRecordDTO recordToCompare = new UtilityRecordDTO(
                4,
                recordToCreate.getName(),
                recordToCreate.getDescription(),
                recordToCreate.getLink()
        );

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
                    assertions.assertThat(createdRecord)
                            .isEqualToComparingFieldByField(recordToCompare);

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
        assertThatThrownBy(() -> service.displayRecordById(id))
                .isInstanceOf(UtilityRecordNotFoundException.class);
    }

    @Test
    @Order(4)
    void testUpdateById(SoftAssertions assertions) {
        // Arrange
        int id = 4;
        UtilityRecord recordToUpdate = new UtilityRecord(id, "Заявка 4", "Описание записи 4", "https://example.com/4");

        // Act
        webTestClient.put()
                .uri("/api/v1/utilityStorage/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordToUpdate)
                .exchange()
                .expectStatus().isOk();

        // Assert
        UtilityRecord retrievedRecord = service.displayRecordById(id);
        assertions.assertThat(retrievedRecord)
                .isEqualToComparingFieldByField(recordToUpdate);
    }

}
