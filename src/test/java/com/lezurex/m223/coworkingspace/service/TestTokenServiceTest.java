package com.lezurex.m223.coworkingspace.service;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TestTokenServiceTest {

  private TestTokenService tokenService = new TestTokenService();

  @Test
  public void test() {
    tokenService.generateToken();
  }

}
