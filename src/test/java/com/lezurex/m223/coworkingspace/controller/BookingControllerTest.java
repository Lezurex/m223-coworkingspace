package com.lezurex.m223.coworkingspace.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import java.time.LocalDate;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.model.Booking;
import com.lezurex.m223.coworkingspace.model.StatusEnum;
import com.lezurex.m223.coworkingspace.model.TimeframeEnum;
import com.lezurex.m223.coworkingspace.service.TestDataService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(BookingController.class)
@TestSecurity(user = "lisbeth.zbinden@bluewin.ch", roles = "member")
public class BookingControllerTest {

  @Inject
  TestDataService testDataService;

  @BeforeEach
  public void reset() {
    testDataService.generateTestData(null);
  }

  @Test
  public void testIndexEndpoint() {
    given().when().get().then().statusCode(200).body(startsWith("[")).and().body(endsWith("]"));
  }

  @Test
  public void testPostEndpoint() {
    var payload = new Booking(LocalDate.now().plusDays(3), TimeframeEnum.AFTERNOON, null, null);

    given().when().contentType(ContentType.JSON).body(payload).post().then().statusCode(200)
        .body("date", is(payload.getDate().toString())).body("timeframe", is("AFTERNOON"));
  }

  @Test
  public void testPostInvalid() {
    var payload = new Booking();

    given().when().contentType(ContentType.JSON).body(payload).post().then().statusCode(400);
  }

  @Test
  public void testPutEndpoint() {
    var payload = new Booking(LocalDate.now().minusDays(1), TimeframeEnum.FULL_DAY,
        StatusEnum.DECLINED, null);

    given().when().contentType(ContentType.JSON).body(payload).put("/1").then().statusCode(200)
        .body("date", is(payload.getDate().toString())).body("timeframe", is("FULL_DAY"));
  }

  @Test
  public void testDeleteEndpoint() {
    given().when().delete("/1").then().statusCode(204);
  }

  @Test
  public void testDeleteNotFound() {
    given().when().delete("/100").then().statusCode(404);
  }

}
