package com.lezurex.m223.coworkingspace.service;

import java.util.Arrays;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.eclipse.microprofile.jwt.Claims;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.jwt.build.Jwt;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestTokenService {
  public String generateToken() {
    String token = Jwt.issuer("https://example.com/issuer").upn("joerg@example.com")
        .groups(new HashSet<>(Arrays.asList("user", "admin")))
        .claim(Claims.birthdate.name(), "2001-07-13").sign();
    return token;
  }

  void printToken(@Observes StartupEvent event) {
    System.out.println(this.generateToken());
  }
}
