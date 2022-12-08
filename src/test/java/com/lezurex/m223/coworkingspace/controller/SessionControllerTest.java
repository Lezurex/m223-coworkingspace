package com.lezurex.m223.coworkingspace.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.model.Credential;
import com.lezurex.m223.coworkingspace.service.TestDataService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(SessionController.class)
public class SessionControllerTest {

  @Inject
  TestDataService testDataService;

  @BeforeEach
  public void reset() {
    testDataService.generateTestData(null);
  }

  @Test
  public void testLoginCorrectMember() {
    var credentials = new Credential("lisbeth.zbinden@bluewin.ch", "RacletteZumZmorgu123");
    given().when().contentType(ContentType.JSON).body(credentials).post().then().statusCode(200)
        .body("role", is("MEMBER"));
  }

  @Test
  public void testLoginCorrectAdmin() {
    var credentials = new Credential("jonathan.meier@coworking.ch", "JonathanFTW123");
    given().when().contentType(ContentType.JSON).body(credentials).post().then().statusCode(200)
        .body("role", is("ADMIN"));
  }

  @Test
  public void testLoginWrongEmail() {
    var credentials = new Credential("elisabeth.zbinden@bluewin.ch", "RacletteZumZmorgu123");
    given().when().contentType(ContentType.JSON).body(credentials).post().then().statusCode(403);
  }

  @Test
  public void testLoginWrongPassword() {
    var credentials = new Credential("lisbeth.zbinden@bluewin.ch", "RacletteZumZmorguuuuu123");
    given().when().contentType(ContentType.JSON).body(credentials).post().then().statusCode(403);
  }

}
