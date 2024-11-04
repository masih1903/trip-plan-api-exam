package app.routes;

import app.config.AppConfig;
import app.config.HibernateConfig;
import app.config.Populator;
import app.daos.TripDAO;
import app.dtos.TripDTO;
import app.enums.Category;
import app.security.controllers.SecurityController;
import app.security.daos.SecurityDAO;
import app.security.exceptions.ValidationException;
import dk.bugelhartmann.UserDTO;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TripRouteTest {

    private static Javalin app;
    private static EntityManagerFactory emf;
    private static String BASE_URL = "http://localhost:7070/api";
    private static TripDAO tripDAO;
    private static SecurityDAO securityDAO;
    private static SecurityController securityController;
    private static Populator populator;
    private static TripDTO t1, t2, t3;
    private static List<TripDTO> trips;
    private static UserDTO userDTO, adminDTO;
    private static String userToken, adminToken;
    private static UserDTO[] users;


    @BeforeAll
    static void init() {

        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfigTest();
        tripDAO = new TripDAO(emf);
        securityDAO = new SecurityDAO(emf);
        securityController = SecurityController.getInstance();
        populator = new Populator(emf, tripDAO);
        app = AppConfig.startServer();

    }

    @BeforeEach
    void setUp() {

        trips = populator.populateTrips();
        t1 = trips.get(0);
        t2 = trips.get(1);
        t3 = trips.get(2);
        users = populator.populateUsers();
        userDTO = users[0];
        adminDTO = users[1];

        try {
            UserDTO verifiedUser = securityDAO.getVerifiedUser(userDTO.getUsername(), userDTO.getPassword());
            UserDTO verifiedAdmin = securityDAO.getVerifiedUser(adminDTO.getUsername(), adminDTO.getPassword());
            userToken = "Bearer " + securityController.createToken(verifiedUser);
            adminToken = "Bearer " + securityController.createToken(verifiedAdmin);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }



    @AfterEach
    void tearDown() {
        populator.cleanUp();

    }

    @AfterAll
    static void closeDown() {
        AppConfig.stopServer(app);
    }

    @Test
    void getTripById() {


        TripDTO tripDTO = given().when().get(BASE_URL + "/trips/1")
                .then().log().all().statusCode(200).extract().as(TripDTO.class);

        assertThat(tripDTO, equalTo(t1));


    }

    @Test
    void getAllTrips() {

        TripDTO[] trips =
                given()
                        .when()
                        .get(BASE_URL + "/trips")
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .extract().as(TripDTO[].class);

        assertThat(trips.length, is(3));
        assertThat(trips, arrayContainingInAnyOrder(equalTo(t1), equalTo(t2), equalTo(t3)));
    }

    @Test
    void createTrip() {

        System.out.println("usertoken: " + userToken);
        System.out.println("admintoken: " + adminToken);


        TripDTO tripDTO = new TripDTO();
        tripDTO.setStartTime(LocalDateTime.of(2024, 7, 10, 15, 0));
        tripDTO.setEndTime(LocalDateTime.of(2024, 7, 20, 10, 0));
        tripDTO.setStartPosition("Maldives");
        tripDTO.setName("Family Beach Vacation");
        tripDTO.setPrice(5000.00);
        tripDTO.setCategory(Category.FAMILY);

        // Make a POST request to create the trip
        TripDTO createdTrip =
                given()
                        .contentType("application/json")
                        .body(tripDTO)
                        .when()
                        .header("Authorization", adminToken)
                        .post(BASE_URL + "/trips")
                        .then()
                        .log()
                        .all()
                        .statusCode(201)
                        .extract()
                        .as(TripDTO.class);

        // Assertions to verify the created trip matches the input data
        assertThat(createdTrip.getStartTime(), equalTo(tripDTO.getStartTime()));
        assertThat(createdTrip.getEndTime(), equalTo(tripDTO.getEndTime()));
        assertThat(createdTrip.getStartPosition(), equalTo(tripDTO.getStartPosition()));
        assertThat(createdTrip.getName(), equalTo(tripDTO.getName()));
        assertThat(createdTrip.getPrice(), equalTo(tripDTO.getPrice()));
        assertThat(createdTrip.getCategory(), equalTo(tripDTO.getCategory()));

    }

    @Test
    void updateTrip() {

        System.out.println("usertoken: " + userToken);
        System.out.println("admintoken: " + adminToken);

        // Create a TripDTO for the updated trip
        TripDTO updatedTrip = new TripDTO();
        updatedTrip.setStartTime(LocalDateTime.of(2025, 1, 15, 6, 0));
        updatedTrip.setEndTime(LocalDateTime.of(2025, 1, 25, 18, 0));
        updatedTrip.setStartPosition("Himalayas");
        updatedTrip.setName("Adventure Mountain Trek Updated");
        updatedTrip.setPrice(1600.00);  // Updated price
        updatedTrip.setCategory(Category.ADVENTURE);

        // Make a PUT request to update the trip
        TripDTO updatedTripResponse =
                given()
                        .contentType("application/json")
                        .body(updatedTrip)
                        .when()
                        .header("Authorization", adminToken)
                        .put(BASE_URL + "/trips/1")
                        .then()
                        .log().all().statusCode(200)
                        .extract()
                        .as(TripDTO.class);

        // Validate the updated fields
        assertThat(updatedTripResponse.getStartTime(), equalTo(updatedTrip.getStartTime()));
        assertThat(updatedTripResponse.getEndTime(), equalTo(updatedTrip.getEndTime()));
        assertThat(updatedTripResponse.getStartPosition(), equalTo(updatedTrip.getStartPosition()));
        assertThat(updatedTripResponse.getName(), equalTo(updatedTrip.getName()));
        assertThat(updatedTripResponse.getPrice(), equalTo(updatedTrip.getPrice()));
        assertThat(updatedTripResponse.getCategory(), equalTo(updatedTrip.getCategory()));


    }

    @Test
    void deleteTrip() {

        given()
                .when()
                .header("Authorization", adminToken)
                .delete(BASE_URL + "/trips/1")
                .then()
                .log()
                .all()
                .statusCode(204);

        TripDTO[] tripDTOS =
                given()
                        .when()
                        .header("Authorization", adminToken)
                        .get(BASE_URL + "/trips")
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .extract()
                        .as(TripDTO[].class);

        assertThat(tripDTOS.length, is(2));
        assertThat(tripDTOS[0], equalTo(t2));
        assertThat(tripDTOS[1], equalTo(t3));
    }
}