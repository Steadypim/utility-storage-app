package dev.steadypim.thewhitehw.homework1.api.grade;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import dev.steadypim.thewhitehw.homework1.AbstractTestcontainers;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.CreateGradeDTO;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = AbstractTestcontainers.class)
@AutoConfigureWebTestClient
@FieldDefaults(level = PRIVATE)
@DBRider
class GradeControllerIT {

    @Autowired
    WebTestClient client;

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/grade/grade_create.json", cleanBefore = true, cleanAfter = true)
    @ExpectedDataSet("datasets/controller/grade/grade_create__expected.json")
    void testCreate() {
        //Arrange
        CreateGradeDTO dtoToCreate = CreateGradeDTO.builder()
                                                   .utilityStorageId(1)
                                                   .comment("kaif")
                                                   .grade(4)
                                                   .build();

        //Act & Assert
        client.post()
              .uri("/grade/create")
              .contentType(APPLICATION_JSON)
              .bodyValue(dtoToCreate)
              .exchange()
              .expectStatus()
              .isOk();
    }

    @Test
    void testCreate_returnBadRequest() {
        //Arrange
        CreateGradeDTO dto = CreateGradeDTO.builder().build();

        //Act & Assert
        client.post().uri("/grade/create")
              .contentType(MediaType.APPLICATION_JSON)
              .bodyValue(dto)
              .exchange()
              .expectStatus().isBadRequest()
              .expectBody()
              .jsonPath("$.errorMessages").isNotEmpty();
    }


    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/grade/grade_search.json", cleanBefore = true, cleanAfter = true)
    void testSearch() {
        //Act & Assert
        client.get().uri(
                      uriBuilder -> uriBuilder
                              .path("/grade/search")
                              .queryParam("recordId", 1)
                              .queryParam("grade", 3)
                              .queryParam("sort", "comment,desc")
                              .build())
              .exchange()
              .expectStatus().isOk()
              .expectBody()
              .jsonPath("$").isNotEmpty()
              .jsonPath("$.totalElements").isEqualTo(2)
              .jsonPath("$.content[0].comment").isEqualTo("wonderful")
              .jsonPath("$.content[0].id").isEqualTo(3)
              .jsonPath("$.content[1].comment").isEqualTo("AOAOAO")
              .jsonPath("$.content[1].id").isEqualTo(2);
    }

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/grade/grade_delete.json", cleanBefore = true, cleanAfter = true)
    @ExpectedDataSet("datasets/controller/grade/grade_delete__expected.json")
    void testDelete() {
        //Arrange
        int id = 1;

        //Act & Assert
        client.delete().uri("/grade/{id}", id)
              .exchange()
              .expectStatus().isOk();
    }
}
