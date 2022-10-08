package org.acme.mapper;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import org.acme.models.BookDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestMapper {
    public Item getItemToStore(BookDto bookDto) {
        Item item = new Item();
        item.withPrimaryKey("Id", bookDto.getId()).withString("Tittle", bookDto.getTittle())
                .withString("Name", bookDto.getName())
                .withString("Author", bookDto.getAuthor())
                .withString("Price", bookDto.getPrice());
        return item;
    }

    public DeleteItemSpec getDeleteItemSpec(String id) {
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("Id", id);
        return deleteItemSpec;
    }

    public UpdateItemSpec getUpdateItemSpec(BookDto bookDto) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", bookDto.getId())
                .withUpdateExpression(prepareUpdateExpression(bookDto))
                .withNameMap(prepareNameMap(bookDto))
                .withValueMap(getValueMap(bookDto))
                .withReturnValues(ReturnValue.ALL_NEW);
        return updateItemSpec;
    }

    private String prepareUpdateExpression(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        StringBuilder updateExpresion = new StringBuilder("set");
        if (bookDto.getName() != null) {
            updateExpresion.append(" #a1=:val1,");
        }
        if (bookDto.getAuthor() != null) {
            updateExpresion.append(" #a2=:val2,");
        }
        if (bookDto.getTittle() != null) {
            updateExpresion.append(" #a3=:val3,");
        }
        if (bookDto.getPrice() != null) {
            updateExpresion.append(" #a4=:val4");
        }
        if (updateExpresion.charAt(updateExpresion.length() - 1) == ',') {
            updateExpresion.deleteCharAt(updateExpresion.length() - 1);
        }
        return updateExpresion.toString();
    }

    private NameMap prepareNameMap(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        NameMap nameMap = new NameMap();
        if (bookDto.getName() != null) {
            nameMap.with("#a1", "Name");
        }
        if (bookDto.getAuthor() != null) {
            nameMap.with("#a2", "Author");
        }
        if (bookDto.getTittle() != null) {
            nameMap.with("#a3", "Tittle");
        }
        if (bookDto.getPrice() != null) {
            nameMap.with("#a4", "Price");
        }
        return nameMap;
    }

    private ValueMap getValueMap(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        ValueMap valueMap = new ValueMap();
        if (bookDto.getName() != null) {
            valueMap.withString(":val1", bookDto.getName());
        }
        if (bookDto.getAuthor() != null) {
            valueMap.withString(":val2", bookDto.getAuthor());
        }
        if (bookDto.getTittle() != null) {
            valueMap.withString(":val3", bookDto.getTittle());
        }
        if (bookDto.getPrice() != null) {
            valueMap.withString(":val4", bookDto.getPrice());
        }
        return valueMap;
    }
}