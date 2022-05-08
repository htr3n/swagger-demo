package au.com.infomedix.rest.swagger.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/hello")
public class HelloResource {

  @Context
  HttpServletRequest request;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response get() {
    log.info("{} received a {} request", getClass().getName(), request.getMethod());
    try {
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }
}
