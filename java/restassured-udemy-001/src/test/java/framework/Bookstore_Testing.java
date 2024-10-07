package framework;

import framework.endpoints.BookStoreAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class Bookstore_Testing extends BookStoreAPI {

    @Test
    public void getAllBooks(){
        Response res = retrieveAllBooks();
        res.then().statusCode(200);
    }

    @Test
    public void associateBookToUser(){
        List<String> bookIsbnList = retrieveAllBooks().jsonPath().getList("books.isbn");
    }
}
