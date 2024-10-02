import apis.BookStoreAPI;
import factory.UserFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pojos.bookstore.User;

import java.util.List;

public class FrameworkRestAssuredJava extends BookStoreAPI {

    User staticUser = UserFactory.staticUser();

    @BeforeSuite
    public void setup() {

    }

    @Test
    public void createUser(){
        User randomUser = UserFactory.createRandomUser();
        Response res = generateUser(randomUser);
        System.out.println("User ID: " + randomUser.getUuid());
        res.then().statusCode(201);
    }

    @Test
    public void authUser(){
        Response res = authenticateUser(staticUser);
        res.then().statusCode(200);
    }

    @Test
    public void authInvalidUser(){

    }

    @Test
    public void authMissingPassword(){

    }

    @Test
    public void generateUserToken(){
        Response res = generateUserToken(staticUser);
        res.then().statusCode(200);
    }

    @Test
    public void getUserDetails(){
        Response res = retrieveUserDetails(staticUser);
        res.then().statusCode(200);
    }

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
