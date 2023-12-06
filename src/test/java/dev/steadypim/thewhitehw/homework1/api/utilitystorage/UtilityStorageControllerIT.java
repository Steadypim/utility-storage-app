package dev.steadypim.thewhitehw.homework1.api.utilitystorage;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import dev.steadypim.thewhitehw.homework1.AbstractTestcontainers;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UpdateUtilityRecordDTO;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@DBRider
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = AbstractTestcontainers.class)
@AutoConfigureWebTestClient
@FieldDefaults(level = PRIVATE)
class UtilityStorageControllerIT {


    @Autowired
    WebTestClient client;


    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/utilitystorage/utility_storage_by_id.json")
    void findByIdTest() {
        //Act & Assert
        client.get()
                .uri("/api/v1/utilityStorage/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$.id").isEqualTo(1);

    }

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/utilitystorage/utility_storage_create.json")
    @ExpectedDataSet(value = "datasets/controller/utilitystorage/utility_storage_create__expected.json")
    void createTest() {
        //Arrange
        CreateUtilityRecordDTO recordToCreate = new CreateUtilityRecordDTO(
                "name",
                "description",
                List.of("https://example.com/1", "https://example.com/2", "https://example.com/3")
        );

        //Act & Assert
        client.post()
                .uri("/api/v1/utilityStorage/create")
                .contentType(APPLICATION_JSON)
                .bodyValue(recordToCreate)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/utilitystorage/utility_storage_search.json")
    void searchTest() {
        //Act & Assert
        client.get().uri(uriBuilder -> uriBuilder
                        .path("/api/v1/utilityStorage/search")
                        .queryParam("name", "snusLover")
                        .queryParam("description", "pipi-pupu")
                        .queryParam("sort", "id,desc")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$.totalElements").isEqualTo(2)
                .jsonPath("$.records[0].id").isEqualTo(2)
                .jsonPath("$.records[0].name").isEqualTo("snusLover")
                .jsonPath("$.records[0].description").isEqualTo("pipi-pupu")
                .jsonPath("$.records[1].id").isEqualTo(1)
                .jsonPath("$.records[1].name").isEqualTo("snusLover")
                .jsonPath("$.records[1].description").isEqualTo("pipi-pupu");
    }

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/utilitystorage/utility_storage_delete_by_id.json")
    @ExpectedDataSet("datasets/controller/utilitystorage/utility_storage_delete__expected.json")
    void deleteByIdTest() {
        //Arrange
        int id = 1;

        client.delete()
                .uri("/api/v1/utilityStorage/{id}", id)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/utilitystorage/utility_storage_update_by_id.json")
    @ExpectedDataSet("datasets/controller/utilitystorage/utility_storage_update_by_id__expected.json")
    void updateById() {
        int id = 1;
        UpdateUtilityRecordDTO recordToUpdate = new UpdateUtilityRecordDTO(
                "updated name",
                "updated description",
                List.of("updated link")
        );

        client.put()
                .uri("/api/v1/utilityStorage/{id}", id)
                .contentType(APPLICATION_JSON)
                .bodyValue(recordToUpdate)
                .exchange()
                .expectStatus().isOk();
    }
}