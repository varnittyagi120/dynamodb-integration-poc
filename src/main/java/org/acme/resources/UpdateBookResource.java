package org.acme.resources;

import com.google.gson.Gson;
import org.acme.models.BookDto;
import org.acme.services.BookService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bookdetails")
public class UpdateBookResource {
    @Inject
    BookService bookService;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BookDto deleteBookDetailsById(BookDto bookDto) {
        System.out.println("Update Book info for Bookdto {}" + new Gson().toJson(bookDto));
        try {
            bookService.updateBookDetails(bookDto);
            return bookDto;
        } catch (Exception e) {
            System.out.println("error occurred while deleting data to db: {}"+ e.getMessage());
            System.out.println("book data to be updated : {}" + bookDto);
            throw e;
        }
    }
}
