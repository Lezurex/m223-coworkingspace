package com.lezurex.m223.coworkingspace.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import com.lezurex.m223.coworkingspace.model.Booking;
import com.lezurex.m223.coworkingspace.model.StatusEnum;
import com.lezurex.m223.coworkingspace.service.ApplicationUserService;
import com.lezurex.m223.coworkingspace.service.BookingService;

@Path("/bookings")
@Tag(name = "Bookings", description = "Handling of bookings.")
@RolesAllowed({"member", "admin"})
public class BookingController {

  @Inject
  BookingService bookingService;

  @Inject
  ApplicationUserService userService;

  @Inject
  @RequestScoped
  SecurityContext ctx;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all bookings.", description = "Returns a list of all bookings.")
  public List<Booking> index() {
    return bookingService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new booking.",
      description = "Creates a new booking and returns the newly created booking.")
  public Booking create(Booking booking) {
    var user = userService.findByEmail(ctx.getUserPrincipal().getName());
    assert user.isPresent();
    booking.setApplicationUser(user.get());
    booking.setStatus(StatusEnum.PENDING);
    return bookingService.createBooking(booking);
  }

  @DELETE
  @Operation(summary = "Deletes a booking.", description = "Deletes a booking irrecoverarble.")
  @Path("/{id}")
  public void delete(@PathParam("id") long id) {
    bookingService.deleteBooking(id);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates a booking.", description = "Updates a booking.")
  @Path("/{id}")
  public Booking update(Booking booking, @PathParam("id") long id) {
    booking.setId(id);
    return bookingService.updateBooking(booking);
  }

}
