package com.lezurex.m223.coworkingspace;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.controller.SessionController;
import com.lezurex.m223.coworkingspace.model.Credential;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(SessionController.class)
public class SessionResourceTest {

  @Test
  public void testLogin() {
    var credentials = new Credential("hans@example.com", "HansFTW123");
    given().when().contentType(ContentType.JSON).body(credentials).post().then().statusCode(200);
  }

}
