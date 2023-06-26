package org.example.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.User;

@Slf4j
@Path("/users")
public class UserResource {

  @GET
  @Path("/{id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Operation(summary = "Get user by ID", description = "Get a user by ID")
  @Parameter(name = "id", description = "The ID used to fetch the user", required = true)
  @ApiResponse(
      responseCode = "200",
      description = "User found",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON,
              schema = @Schema(implementation = User.class)))
  @ApiResponse(responseCode = "404", description = "User not found")
  @ApiResponse(responseCode = "500", description = "Generic server error")
  public Response get(@PathParam("id") final String id) {
    try {
      User user = User.builder().build();
      if (user != null) {
        return Response.ok().entity(user).build();
      } else {
        return Response.status(Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Delete an existing user by ID",
      description = "Delete an existing user by ID")
  @Parameter(name = "id", description = "The ID of the user to be deleted", required = true)
  @ApiResponse(responseCode = "200", description = "User deleted successfully")
  @ApiResponse(responseCode = "404", description = "User not found")
  @ApiResponse(responseCode = "500", description = "Generic server error")
  public Response delete(@PathParam("id") String id) {
    try {
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Create a new user")
  @RequestBody(
      description = "The data for creating a new user",
      required = true,
      content =
          @Content(
              schema = @Schema(implementation = User.class),
              mediaType = MediaType.APPLICATION_JSON))
  @ApiResponse(responseCode = "201", description = "User created successfully")
  @ApiResponse(responseCode = "400", description = "Invalid input supplied")
  @ApiResponse(responseCode = "500", description = "Generic server error")
  public Response create(User user) {
    try {
      return Response.status(Status.CREATED).build();
    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Update an existing user")
  @Parameter(
      name = "id",
      description = "The ID used to find the user to update",
      required = true,
      schema = @Schema(type = "string"))
  @RequestBody(
      description = "The user data to be updated",
      required = true,
      content =
          @Content(
              schema = @Schema(implementation = User.class),
              mediaType = MediaType.APPLICATION_JSON))
  @ApiResponse(responseCode = "200", description = "User updated successfully")
  @ApiResponse(responseCode = "400", description = "Invalid input supplied")
  @ApiResponse(responseCode = "404", description = "User not found")
  @ApiResponse(responseCode = "500", description = "Generic server error")
  public Response update(@PathParam("id") String id, User user) {
    try {
      return Response.status(Status.CREATED).build();
    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }
}
