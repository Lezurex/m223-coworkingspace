package com.lezurex.m223.coworkingspace.controller;

import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import com.lezurex.m223.coworkingspace.model.Booking;
import com.lezurex.m223.coworkingspace.service.ApplicationUserService;
import com.lezurex.m223.coworkingspace.service.BookingService;
import io.quarkus.security.ForbiddenException;

@Path("/users/{id}/bookings")
public class UserBookingsController {

  @Inject
  BookingService bookingService;
  @Inject
  ApplicationUserService userService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Indexes all bookings of a user",
      description = "Gets all bookings a user made.")
  @RolesAllowed({"member", "admin"})
  public Set<Booking> index(@Context SecurityContext ctx, @PathParam("id") Long userId) {
    var userOptional = userService.findByEmail(ctx.getUserPrincipal().getName());
    assert userOptional.isPresent();
    var user = userOptional.get();

    switch (user.getRole()) {
      case ADMIN:
        return userService.findById(userId).getBookings();
      case MEMBER:
        if (user.getId() != userId) {
          throw new ForbiddenException();
        }
        return user.getBookings();
      default:
        throw new ForbiddenException();
    }
  }

}
