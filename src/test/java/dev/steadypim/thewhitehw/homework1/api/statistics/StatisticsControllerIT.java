package dev.steadypim.thewhitehw.homework1.api.statistics;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import dev.steadypim.thewhitehw.homework1.AbstractTestcontainers;
import dev.steadypim.thewhitehw.homework1.api.statistics.dtos.StatisticsDTO;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void getStatisticsTest() {
        //Arrange
        StatisticsDTO statisticsDto = client.get()
                                            .uri("/statistics")
                                            .exchange()
                                            .expectStatus().isOk()
                                            .expectBody(StatisticsDTO.class)
                                            .returnResult()
                                            .getResponseBody();

        //Assert
        assertNotNull(statisticsDto);
        assertEquals(4, statisticsDto.totalRecords());
        assertEquals(13, statisticsDto.totalGrades());
        assertEquals(4.615384615384615, statisticsDto.averageGradeOfEntireStorage());
        assertEquals(1, statisticsDto.numberOfRecordsWithMaxAverageGrade());
        assertEquals(25.0, statisticsDto.percentageOfRecordsWithMaxAverageGrade());
        assertEquals(3, statisticsDto.numberOfRecordsWithAverageGradeFourOrHigher());
        assertEquals(75.0, statisticsDto.percentageOfRecordsWithAverageGradeFourOrHigher());
        assertEquals(3, statisticsDto.numberOfRecordsWithoutGradesBelowFour());
        assertEquals(75.0, statisticsDto.percentageOfRecordsWithoutGradesBelowFour());
        assertEquals(1, statisticsDto.numberOfRecordsWithoutGrades());
    }
}
