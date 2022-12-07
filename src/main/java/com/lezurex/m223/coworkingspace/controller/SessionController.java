package com.lezurex.m223.coworkingspace.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.lezurex.m223.coworkingspace.model.Credential;
import com.lezurex.m223.coworkingspace.service.SessionService;

@Path("/session")
public class SessionController {

  @Inject
  SessionService sessionService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(Credential credential) {
    return sessionService.authenticate(credential);
  }

}
