package com.lezurex.m223.coworkingspace;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.controller.ApplicationUserController;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.RoleEnum;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(ApplicationUserController.class)
@TestSecurity(user = "hans@test.com", roles = "user")
public class ApplicationUserResourceTest {

    @Test
    public void testIndexEndpoint() {
        given().when().get().then().statusCode(200).body(startsWith("[")).and().body(endsWith("]"));
    }

    @Test
    public void testPostEndpoint() {
        var payload = new ApplicationUser("thanam.pangri@tbsana.ch", "Thanam", "Pangri",
                "PangriFTW123", RoleEnum.MEMBER);

        given().when().contentType(ContentType.JSON).body(payload).post().then().statusCode(200)
                .body("email", is("thanam.pangri@tbsana.ch"));
    }

    @Test
    public void testPutEndpoint() {
        var payload = new ApplicationUser("thanam.pangri@tbsana.ch", "Thanam", "Pangri",
                "PangriFTW123", RoleEnum.MEMBER);

        given().when().contentType(ContentType.JSON).body(payload).post();
        payload.setPasswordHash("ThanamFTW123");
        payload.setRole(RoleEnum.ADMIN);
        given().when().contentType(ContentType.JSON).body(payload).put("/3").then().statusCode(200)
                .body("passwordHash", is("ThanamFTW123"));
    }

    @Test
    public void testDeleteEndpoint() {
        given().when().delete("/2").then().statusCode(204);
    }

}
