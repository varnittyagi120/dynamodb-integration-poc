package org.acme.resources;

import com.google.gson.Gson;
import org.acme.models.BookDto;
import org.acme.services.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bookdetails")
public class DeleteBookResource {
    @Inject
    BookService bookService;

    @DELETE
    @Path("{Id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBookDetailsById(@PathParam("Id") String id) {
        System.out.println("Delete Book info Id {}" + id);
        try {
            bookService.deleteBookDetailsById(id);
            //bookService.deleteDataByIdWithDynamodbMapper(id);
            return Response.ok("Book data has successfully deleted coressponding to id : "+id).build();
        } catch (Exception e) {
            System.out.println("error occurred while deleting data to db: {}"+ e.getMessage());
            System.out.println("Book Id to be deleted : {}" + id);
            throw e;
        }
    }
}
