package com.lezurex.m223.coworkingspace.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import com.lezurex.m223.coworkingspace.model.Credential;
import com.lezurex.m223.coworkingspace.service.SessionService;

@Path("/session")
@Tag(name = "Session", description = "Allows a user to manage his session.")
public class SessionController {

  @Inject
  SessionService sessionService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Allows the user to login.",
      description = "This endpoint allows the user to login. On successful login, a JWT (valid for 24 hours) will be returned in the Authorization header.")
  public Response login(Credential credential) {
    return sessionService.authenticate(credential);
  }

}
