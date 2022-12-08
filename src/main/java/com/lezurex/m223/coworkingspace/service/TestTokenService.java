package com.lezurex.m223.coworkingspace.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.jwt.build.Jwt;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestTokenService {
  public String generateToken() {
    String token = Jwt.issuer("https://example.com/issuer").upn("jonathan.meier@coworking.ch")
        .expiresIn(Duration.ofDays(1)).groups(new HashSet<>(Arrays.asList("member", "admin")))
        .sign();
    return token;
  }

  void printToken(@Observes StartupEvent event) {
    System.out.println(this.generateToken());
  }
}
