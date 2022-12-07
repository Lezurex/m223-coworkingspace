package com.lezurex.m223.coworkingspace.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.Credential;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {
  @Inject
  ApplicationUserService applicationUserService;

  public Response authenticate(Credential credential) {
    Optional<ApplicationUser> principal = applicationUserService.findByEmail(credential.getEmail());
    try {
      if (principal.isPresent() && principal.get().getPassword().equals(credential.getPassword())) {
        String token = Jwt.issuer("https://example.com/issuer").upn("joerg@example.com")
            .groups(new HashSet<>(Arrays.asList("user", "admin")))
            .claim(Claims.birthdate.name(), "2001-07-13").sign();
        return Response.ok(principal.get()).cookie(new NewCookie("punchclock", token))
            .header("Authorization", "Bearer " + token).build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }
    return Response.status(403).build();
  }
}
