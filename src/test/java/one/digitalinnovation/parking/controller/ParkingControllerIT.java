package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {

        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.SC_OK)
    }

    @Test
    void whenCreateThenCheckIsCrteated() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("Amarela");
        createDTO.setLicence("WRT-5555");
        createDTO.setModel("Brasilia");
        createDTO.setState("SP");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("licence", Matchers.equalTo("WRT-5555"))
                .body("color", Matchers.equalTo("Amarela"));
    }
}