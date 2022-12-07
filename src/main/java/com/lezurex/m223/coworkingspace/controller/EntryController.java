package com.lezurex.m223.coworkingspace.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import com.lezurex.m223.coworkingspace.model.Entry;
import com.lezurex.m223.coworkingspace.service.ApplicationUserService;
import com.lezurex.m223.coworkingspace.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
@RolesAllowed({"user", "admin"})
public class EntryController {

    @Inject
    EntryService entryService;
    @Inject
    ApplicationUserService userService;
    @Inject

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Entry> index() {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new entry.",
            description = "Creates a new entry and returns the newly added entry.")
    public Entry create(Entry entry, @Context SecurityContext ctx) {
        var user = userService.findByEmail(ctx.getUserPrincipal().getName());
        if (user.isPresent()) {
            entry.setApplicationUser(user.get());
            return entryService.createEntry(entry);
        }
        throw new BadRequestException("User missing");
    }

    @DELETE
    @Operation(summary = "Deletes an entry.", description = "Deletes an entry irrecoverarble.")
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        entryService.deleteEntry(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an entry.", description = "Updates an entry.")
    @Path("/{id}")
    public Entry update(Entry entry, @PathParam("id") long id, @Context SecurityContext ctx) {
        var user = userService.findByEmail(ctx.getUserPrincipal().getName());
        if (user.isPresent()) {
            entry.setId(id);
            entry.setApplicationUser(user.get());
            return entryService.updateEntry(entry);
        }
        throw new BadRequestException("User missing");
    }
}
