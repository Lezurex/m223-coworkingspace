package com.lezurex.m223.coworkingspace.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.Credential;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

@ApplicationScoped
public class SessionService {
  @Inject
  ApplicationUserService applicationUserService;

  public Response authenticate(Credential credential) {
    Optional<ApplicationUser> principal = applicationUserService.findByEmail(credential.getEmail());
    try {
      if (principal.isPresent()
          && principal.get().getPasswordHash().equals(credential.getPassword())) {
        var jwt = Jwt.issuer("https://example.com/issuer").upn(principal.get().getEmail())
            .expiresIn(Duration.ofDays(1)).groups(new HashSet<>(Arrays.asList("user", "admin")));
        setRoles(jwt, principal.get());
        String token = jwt.sign();
        return Response.ok(principal.get()).cookie(new NewCookie("coworking", token))
            .header("Authorization", "Bearer " + token).build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }
    return Response.status(403).build();
  }

  private void setRoles(JwtClaimsBuilder jwt, ApplicationUser user) {
    List<String> roles = new ArrayList<>();
    switch (user.getRole()) {
      case ADMIN:
        roles.add("admin");
      case MEMBER:
        roles.add("member");
    }
    jwt.groups(new HashSet<>(roles));
  }
}
