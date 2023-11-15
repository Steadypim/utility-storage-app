package dev.steadypim.thewhitehw.homework1.api.grade;

import dev.steadypim.thewhitehw.homework1.api.grade.dtos.CreateGradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.GradeDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepositoryImpl;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SoftAssertionsExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GradeControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UtilityStorageRepositoryImpl repository;

    @BeforeEach
    void setupData() {
        UtilityStorage record1 = new UtilityStorage(1, "Запись 1", "Описание записи 1", "https://example.com/1");

        repository.create(record1);
    }

    @Test
    @Order(1)
    void testCreate() {
        CreateGradeDTO dto = CreateGradeDTO.builder()
                .recordId(1)
                .comment("nice")
                .grade(3)
                .build();

        GradeDTO createdGrade = webTestClient.post().uri("grade/create")
                .contentType(APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GradeDTO.class)
                .returnResult().getResponseBody();

        assert createdGrade != null;
        assertThat(createdGrade.getGrade()).isEqualTo(dto.getGrade());
        assertThat(createdGrade.getId()).isEqualTo(1);
        assertThat(createdGrade.getComment()).isEqualTo(dto.getComment());
    }

    @Test
    @Order(2)
    void testCreate_returnBadRequest() {
        //Arrange
        CreateGradeDTO dto = CreateGradeDTO.builder().build();

        //Act & Assert
        webTestClient.post().uri("/grade/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorMessage").isNotEmpty();
    }

    @Test
    @Order(3)
    void testFindAllByRecordId() {
        //Arrange
        int recordId = 1;

        //Act & Assert
        webTestClient.get().uri("/grade/{recordId}", recordId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0]").isNotEmpty()
                .jsonPath("$[0].recordId").isEqualTo(recordId);
    }

    @Test
    @Order(4)
    void testDelete() {
        //Arrange
        int id = 1;
        int recordId = 1;

        //Act & Assert
        webTestClient.delete().uri("/grade/{id}", id)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/grade/{recordId}", recordId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }
}
