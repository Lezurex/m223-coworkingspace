package com.lezurex.m223.coworkingspace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lezurex.m223.coworkingspace.model.Credential;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SessionServiceTest {

  @Inject
  SessionService service;

  @Inject
  TestDataService testDataService;

  @BeforeEach
  public void reset() {
    testDataService.generateTestData(null);
  }

  @Test
  @TestTransaction
  public void testAuthentication() {
    var credential = new Credential("lisbeth.zbinden@bluewin.ch", "RacletteZumZmorgu123");
    var response = service.authenticate(credential);
    assertEquals(response.getStatus(), 200);
  }

}
