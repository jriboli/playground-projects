import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pojos.bookstore.ISBN;
import pojos.bookstore.UserBookCollection;
import pojos.bookstore.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.path.json.JsonPath.from;

public class AdvancedRestAssuredJava {
    private RequestSpecification req;
    private Response res;

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        req = RestAssured.given().log().all();
    }

    @AfterMethod
    public void wrapUp() {
        res.then().log().all();
    }

    @Test
    public void createUser(){
        User existingUser = new User();
        existingUser.setUserName("rocket99");
        existingUser.setPassword("P@ssword99");

        res = req.headers("content-type", "application/json").body(existingUser).post("/account/v1/user");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);
    }

    @Test
    public void authUser(){
        User existingUser = new User();
        existingUser.setUserName("rocket99");
        existingUser.setPassword("P@ssword99");

        res = req.headers("content-type", "application/json").body(existingUser).post("/account/v1/authorized");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);
    }

    @Test
    public void authInvalidUser(){
        User existingUser = new User();
        existingUser.setUserName("rocket99");
        existingUser.setPassword("P@ssword98");

        res = req.headers("content-type", "application/json").body(existingUser).post("/account/v1/authorized");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);

        res.then().statusCode(404);
    }

    @Test
    public void authMissingPassword(){
        User existingUser = new User();
        existingUser.setUserName("rocket99");

        res = req.headers("content-type", "application/json").body(existingUser).post("/account/v1/authorized");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);

        res.then().statusCode(400);
    }

    @Test
    public void generateUserToken(){
        User existingUser = new User();
        existingUser.setUserName("rocket99");
        existingUser.setPassword("P@ssword99");

        res = req.headers("content-type", "application/json").body(existingUser).post("/account/v1/generatetoken");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);

        String token = new JsonPath(resBody).getString("token");
        System.out.println("Token: " + token);

        // UUID = 67f671f4-4dd7-40e9-b9b6-a3724afb8e0f
        res.then().body("status", equalTo("Success"));
    }

    @Test
    public void getUserDetails(){
        User existingUser = new User();
        existingUser.setUserName("rocket99");
        existingUser.setPassword("P@ssword99");

        res = req
                .headers("authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InJvY2tldDk5IiwicGFzc3dvcmQiOiJQQHNzd29yZDk5IiwiaWF0IjoxNzI3NTYyMTAxfQ.TVOCv78ERnHNf6TsTItD84MsWdWgsfvtoUwI_AicYAw")
                .get("/account/v1/user/" + "67f671f4-4dd7-40e9-b9b6-a3724afb8e0f");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);
    }

    @Test
    public void getAllBooks(){
        res = req.get("/bookstore/v1/books");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);
    }

    @Test
    public void associateBookToUser(){
        // Grab a Book ISBN
        res = req.get("/bookstore/v1/books");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);

        List<String> bookIsbn = from(resBody).getList("books.isbn");
        System.out.println("Book ISBN Results: " + bookIsbn);
        List<ISBN> isbnList = new ArrayList<>();
        isbnList.add(new ISBN(bookIsbn.get(0)));

        // UUID = 67f671f4-4dd7-40e9-b9b6-a3724afb8e0f
        // Grab new User Token

        UserBookCollection request = new UserBookCollection();
        request.setUserId("67f671f4-4dd7-40e9-b9b6-a3724afb8e0f");
        request.setCollectionOfIsbns(isbnList);

        Response res2 = req
                .headers("content-type", "application/json")
                .headers("authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InJvY2tldDk5IiwicGFzc3dvcmQiOiJQQHNzd29yZDk5IiwiaWF0IjoxNzI3NTYyMTAxfQ.TVOCv78ERnHNf6TsTItD84MsWdWgsfvtoUwI_AicYAw")
                .body(request)
                .post("/bookstore/v1/books");
        String res2Body = res2.getBody().asString();
        System.out.println("Response2: " + res2Body);
    }
}
