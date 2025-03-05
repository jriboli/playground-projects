package framework;

import framework.endpoints.AccountAPI;
import framework.endpoints.BookStoreAPI;
import framework.factory.UserFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import framework.playloads.bookstore.User;

import java.util.List;

public class Account_Testing extends AccountAPI {

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
}
