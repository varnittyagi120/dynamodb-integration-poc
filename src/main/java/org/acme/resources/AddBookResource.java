package org.acme.resources;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.google.gson.Gson;
import org.acme.models.BookDto;
import org.acme.services.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bookdetails")
public class AddBookResource {
    @Inject
    BookService bookService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BookDto addBookDetails(BookDto bookDto) {
        System.out.println("add book info {}" + new Gson().toJson(bookDto));
        try {
            return bookService.addBookDetails(bookDto);
//            bookService.saveBookDetailsWithDynamodbMapper(bookDto);
//            return bookDto;
        } catch (Exception e) {
            System.out.println("error occurred while adding data to db: {}"+ e.getMessage());
            System.out.println("book details: {}" + new Gson().toJson(bookDto));
            throw e;
        }
    }
}
