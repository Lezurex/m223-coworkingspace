package com.lezurex.m223.coworkingspace.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import com.lezurex.m223.coworkingspace.model.Category;
import com.lezurex.m223.coworkingspace.service.CategoryService;

@Path("/categories")
@RolesAllowed({"user", "admin"})
@Tag(name = "Categories", description = "Handling of categories.")
public class CategoryController {

  @Inject
  CategoryService categoryService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
  public List<Category> index() {
    return categoryService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new category.",
      description = "Creates a new category and returns the newly added category.")
  public Category create(Category category) {
    return categoryService.createCategory(category);
  }

  @DELETE
  @Operation(summary = "Deletes a category.", description = "Deletes a category irrecoverarble.")
  @Path("/{id}")
  public void delete(@PathParam("id") long id) {
    categoryService.deleteCategory(id);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates a category.", description = "Updates a category.")
  @Path("/{id}")
  public Category update(Category category, @PathParam("id") long id) {
    category.setId(id);
    return categoryService.updateCategory(category);
  }

}
