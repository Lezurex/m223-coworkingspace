package com.lezurex.m223.coworkingspace.controller;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
@TestHTTPEndpoint(UserBookingsController.class)
public class UserBookingControllerTest {

  @Test
  @TestSecurity(user = "lisbeth.zbinden@bluewin.ch", roles = "member")
  public void testAsMember() {
    given().pathParam("id", 2).when().get().then().statusCode(200);
    given().pathParam("id", 1).when().get().then().statusCode(403);
  }

  @Test
  @TestSecurity(user = "jonathan.meier@coworking.ch", roles = "admin")
  public void testAsAdmin() {
    given().pathParam("id", 2).when().get().then().statusCode(200);
    given().pathParam("id", 1).when().get().then().statusCode(200);
  }

  @Test
  public void testAsNobody() {
    given().pathParam("id", 2).when().get().then().statusCode(401);
  }
}
