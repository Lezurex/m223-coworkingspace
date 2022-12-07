package com.lezurex.m223.coworkingspace.controller;

import static io.restassured.RestAssured.given;
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
public class SessionResourceController {

  @Inject
  TestDataService testDataService;

  @BeforeEach
  public void reset() {
    testDataService.generateTestData(null);
  }

  @Test
  public void testLogin() {
    var credentials = new Credential("lisbeth.zbinden@bluewin.ch", "RacletteZumZmorgu123");
    given().when().contentType(ContentType.JSON).body(credentials).post().then().statusCode(200);
  }

}
