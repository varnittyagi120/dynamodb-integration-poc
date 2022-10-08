package org.acme.services;

import com.google.gson.Gson;
import org.acme.dao.DynamodbOperationsDao;
import org.acme.models.BookDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BookService {
    @Inject
    DynamodbOperationsDao dynamodbOperationsDao;

    public BookDto fetchBookById(String id) {
        System.out.println("Request to fetch the data");
        return dynamodbOperationsDao.getById(id);
    }

    public BookDto addBookDetails(BookDto bookDto){
        System.out.println("Request to add the data to dynamodb : {}"+new Gson().toJson(bookDto));
        return dynamodbOperationsDao.addBookDetailsToDB(bookDto);
    }

    public void deleteBookDetailsById(String id) {
        System.out.println("Request to delete the data to dynamodb : {}"+id);
        dynamodbOperationsDao.deleteBookDetailsFromDB(id);
    }

    public BookDto updateBookDetails(BookDto bookDto){
        System.out.println("Request to update the data to dynamodb : {}"+new Gson().toJson(bookDto));
        return dynamodbOperationsDao.updateBookDetailsToDB(bookDto);
    }

    public void saveBookDetailsWithDynamodbMapper(BookDto bookDto){
        dynamodbOperationsDao.saveBookDataWithDynamodbMapper(bookDto);
    }

    public BookDto updateBookingDetailsWithDynamodbMapper(String id){
        return dynamodbOperationsDao.getDataByIdWithDynamodbMapper(id);
    }

    public void deleteDataByIdWithDynamodbMapper(String id){
        deleteDataByIdWithDynamodbMapper(id);
    }

}
