package org.acme.mapper;

import com.amazonaws.services.dynamodbv2.document.Item;
import org.acme.models.BookDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class ResponseMapper {
    public BookDto getBookDto(Item item) {
        if (Objects.isNull(item)) {
            return null;
        }
        BookDto bookDto = new BookDto();
        bookDto.setId(item.get("Id").toString());
        bookDto.setAuthor(item.get("Author").toString());
        bookDto.setName(item.get("Name").toString());
        bookDto.setPrice(item.get("Price").toString());
        bookDto.setTittle(item.get("Tittle").toString());
        return bookDto;
    }

}
