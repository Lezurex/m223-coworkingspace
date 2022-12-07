package com.lezurex.m223.coworkingspace.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ApplicationUserServiceTest {

  @Inject
  ApplicationUserService service;

  @Inject
  TestDataService testDataService;

  @BeforeEach
  public void reset() {
    testDataService.generateTestData(null);
  }

  @Test
  public void testFindByEmail() {
    var user = service.findByEmail("lisbeth.zbinden@bluewin.ch");
    assertTrue(user.isPresent());
  }

}
