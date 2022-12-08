package com.lezurex.m223.coworkingspace.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.RoleEnum;
import com.lezurex.m223.coworkingspace.service.TestDataService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(ApplicationUserController.class)
@TestSecurity(user = "hans@test.com", roles = "member")
public class ApplicationUserControllerTest {

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
    var payload = new ApplicationUser("thanam.pangri@tbsana.ch", "Thanam", "Pangri", "PangriFTW123",
        RoleEnum.MEMBER);

    given().when().contentType(ContentType.JSON).body(payload).post().then().statusCode(200)
        .body("email", is("thanam.pangri@tbsana.ch"));
  }

  @Test
  public void testPutEndpoint() {
    var payload = new ApplicationUser("thanam.pangri@tbsana.ch", "Thanam", "Pangri", "ThanamFTW123",
        RoleEnum.ADMIN);

    given().when().contentType(ContentType.JSON).body(payload).put("/3").then().statusCode(200)
        .body("passwordHash", is("ThanamFTW123")).toString();
  }

  @Test
  public void testDeleteEndpoint() {
    given().when().delete("/2").then().statusCode(204);
  }

}
