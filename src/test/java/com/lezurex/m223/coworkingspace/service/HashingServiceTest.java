package com.lezurex.m223.coworkingspace.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HashingServiceTest {

  @Inject
  HashingService hashingService;

  @Test
  public void testHasingCorrect() {
    var password = "Hallo1234";
    var hash = hashingService.hashPassword(password);
    assertTrue(hashingService.verifyPassword(password, hash));
  }

  @Test
  public void testHasingWrong() {
    var password = "Hallo1234";
    var hash = hashingService.hashPassword(password);
    assertFalse(hashingService.verifyPassword("hallo1234", hash));
  }
}
