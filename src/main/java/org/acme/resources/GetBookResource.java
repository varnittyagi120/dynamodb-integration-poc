package org.acme.resources;

import org.acme.models.BookDto;
import org.acme.services.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bookdetails")
public class GetBookResource {
    @Inject
    BookService dynamodbService;

    @GET
    @Path("/{Id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BookDto fetchBookById(@PathParam("Id") String id) {
        System.out.println("fetch book info by id : {}" + id);
        try {
            return dynamodbService.fetchBookById(id);
            //return dynamodbService.updateBookingDetailsWithDynamodbMapper(id);
        } catch (Exception e) {
            System.out.println("error occurred while fetching data from db: {}"+ e.getMessage());
            System.out.println("book id to be fetched: {}" + id);
            throw e;
        }
    }
}
