package com.lezurex.m223.coworkingspace;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.controller.EntryController;
import com.lezurex.m223.coworkingspace.model.Entry;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(EntryController.class)
@TestSecurity(user = "hans@example.com", roles = "user")
public class EntryResourceTest {

  @Test
  public void testIndexEndpoint() {
    given().when().get().then().statusCode(200).body(startsWith("[")).and().body(endsWith("]"));
  }

  @Test
  public void testPostEndpoint() {
    var payload =
        new Entry(LocalDateTime.parse("2022-11-30T10:00"), LocalDateTime.parse("2022-11-30T17:00"));

    given().when().contentType(ContentType.JSON).body(payload).post().then().statusCode(200)
        .body("checkIn", is("2022-11-30T10:00:00")).body("checkOut", is("2022-11-30T17:00:00"));
  }

  @Test
  public void testPutEndpoint() {
    var payload =
        new Entry(LocalDateTime.parse("2022-11-30T10:00"), LocalDateTime.parse("2022-11-30T17:00"));

    given().when().contentType(ContentType.JSON).body(payload).post();
    payload.setCheckIn(LocalDateTime.parse("2022-11-30T09:00"));
    given().when().contentType(ContentType.JSON).body(payload).put("/4").then().statusCode(200)
        .body("checkIn", is("2022-11-30T09:00:00"));
  }

  @Test
  public void testDeleteEndpoint() {
    var payload =
        new Entry(LocalDateTime.parse("2022-11-30T10:00"), LocalDateTime.parse("2022-11-30T17:00"));

    given().when().contentType(ContentType.JSON).body(payload).post();
    given().when().delete("/4").then().statusCode(204);
  }

}
