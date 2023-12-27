package dev.steadypim.thewhitehw.homework1.api.statistics;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import dev.steadypim.thewhitehw.homework1.AbstractTestcontainers;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@DBRider
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = AbstractTestcontainers.class)
@AutoConfigureWebTestClient
@FieldDefaults(level = PRIVATE)
class StatisticsControllerIT {

    @Autowired
    WebTestClient client;

    @Test
    @SneakyThrows
    @DataSet(value = "datasets/controller/statistics/statistics_get.json", cleanBefore = true, cleanAfter = true)
    void getStatisticsTest(){
        client.get()
                .uri("/statistics")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isMap()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$.totalRecords").isEqualTo(4)
                .jsonPath("$.totalGrades").isEqualTo(13)
                .jsonPath("$.averageGradeOfEntireStorage").isEqualTo(4.615384615384615)
                .jsonPath("$.numberOfRecordsWithMaxAverageGrade").isEqualTo(1)
                .jsonPath("$.percentageOfRecordsWithMaxAverageGrade").isEqualTo(25.0)
                .jsonPath("$.numberOfRecordsWithAverageGradeFourOrHigher").isEqualTo(3)
                .jsonPath("$.percentageOfRecordsWithAverageGradeFourOrHigher").isEqualTo(75.0)
                .jsonPath("$.numberOfRecordsWithoutGradesBelowFour").isEqualTo(3)
                .jsonPath("$.percentageOfRecordsWithoutGradesBelowFour").isEqualTo(75.0)
                .jsonPath("$.numberOfRecordsWithoutGrades").isEqualTo(1);
    }
}
