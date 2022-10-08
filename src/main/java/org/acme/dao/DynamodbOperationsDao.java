package org.acme.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.google.gson.Gson;
import org.acme.constants.DynamodbPocConstants;
import org.acme.mapper.RequestMapper;
import org.acme.mapper.ResponseMapper;
import org.acme.models.BookDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DynamodbOperationsDao {
    @Inject
    DynamoDB dynamoDBClient;

    @Inject
    ResponseMapper responseMapper;

    @Inject
    RequestMapper requestMapper;

    @Inject
    DynamoDBMapper dynamoDBMapper;

    public BookDto getById(String id){
        Item item = dynamoDBClient.getTable(DynamodbPocConstants.Book_Data_Table_Name).getItem("Id", id);
        return responseMapper.getBookDto(item);
    }

    public BookDto addBookDetailsToDB(BookDto bookDto){
        PutItemOutcome dbResponse = dynamoDBClient.getTable(DynamodbPocConstants.Book_Data_Table_Name).putItem(requestMapper.getItemToStore(bookDto));
        System.out.println("Database reponse after adding data to db"+ new Gson().toJson(dbResponse));
        return bookDto;
    }

    public DeleteItemOutcome deleteBookDetailsFromDB(String id){
        DeleteItemSpec deleteItemSpec = requestMapper.getDeleteItemSpec(id);
        DeleteItemOutcome deleteItemOutcome = dynamoDBClient.getTable(DynamodbPocConstants.Book_Data_Table_Name).deleteItem(deleteItemSpec);
        System.out.println("Database reponse after deleting data to db"+ new Gson().toJson(deleteItemOutcome));
        return deleteItemOutcome;
    }

    public BookDto updateBookDetailsToDB(BookDto bookDto){
        UpdateItemOutcome updateItemOutcome = dynamoDBClient.getTable(DynamodbPocConstants.Book_Data_Table_Name).updateItem(requestMapper.getUpdateItemSpec(bookDto));
        System.out.println("Database reponse after updating data to db"+ new Gson().toJson(updateItemOutcome));
        return bookDto;
    }

    public void saveBookDataWithDynamodbMapper(BookDto bookDto){
          dynamoDBMapper.save(bookDto);
    }

    public BookDto getDataByIdWithDynamodbMapper(String id){
        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();
        BookDto bookDto = dynamoDBMapper.load(BookDto.class, id, config);
        System.out.println("Retrieved the previously updated item:");
        System.out.println(new Gson().toJson(bookDto));
        return bookDto;
    }

    public void deleteDataByIdWithDynamodbMapper(String id){
        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();
        BookDto bookDto = dynamoDBMapper.load(BookDto.class, id, config);
        System.out.println(new Gson().toJson(bookDto));
        dynamoDBMapper.delete(bookDto);
        System.out.println("Data deleted successfully.");
    }
}
