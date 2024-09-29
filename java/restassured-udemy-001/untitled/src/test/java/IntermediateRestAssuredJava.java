import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pojos.reqres.Registration;
import pojos.reqres.User;
import pojos.reqres.UserDetails;

public class IntermediateRestAssuredJava {

    private RequestSpecification req;
    private Response res;

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        req = RestAssured.given().log().all();
    }

    @AfterMethod
    public void wrapUp() {
        res.then().log().all();
    }

    @Test
    public void getUsers(){
        res = req.get("/users");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);
    }

    @Test
    public void createUser() {
        User newUser = new User("Rocket Raccoon", "Guardian");
        res = req.body(newUser).post("/users");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);

        res.then().statusCode(201);
    }

    @Test
    public void updateUserDetails() {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetails existingUser;

        res = req.get("/users/1");
        String resJson = res.asString();
        try {
            existingUser = objectMapper.readValue(resJson, UserDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        User updatedUser = new User();
        updatedUser.setJob("Trashman");
        updatedUser.setName(existingUser.getData().getFirst_name() + " " + existingUser.getData().getLast_name());

        Response res2 = req.body(updatedUser).put("/users/" + new JsonPath(res.asString()).getInt("data.id"));
        System.out.println("Response: " + res2.getBody().asString());
    }

    @Test
    public void updateUserDetails2() {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetails existingUser;

        res = req.get("/users/2");
//        String resJson = res.asString();
//        try {
//            existingUser = objectMapper.readValue(resJson, UserDetails.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        existingUser = res.getBody().as(UserDetails.class);

        User updatedUser = new User();
        updatedUser.setJob("Trashman");
        updatedUser.setName(existingUser.getData().getFirst_name() + " " + existingUser.getData().getLast_name());

        Response res2 = req.body(updatedUser).patch("/users/" + new JsonPath(res.asString()).getInt("data.id"));
        System.out.println("Response: " + res2.getBody().asString());
    }

    @Test
    public void deleteUser(){

        // Hardcoded User ID
        int userID = 99;

        res = req.delete("/users/" + userID);
        String resBody = res.getBody().asString();

        System.out.println("Response: " + resBody);

        res.then().statusCode(204);
    }

    @Test
    public void RegistrationSuccessful(){
        Registration reg = new Registration();
        reg.setEmail("rocketraccoon99@gmail.com");
        reg.setPassword("password99");

        res = req.body(reg).post("/register");
        String resBody = res.getBody().asString();

        System.out.println("Response: " + resBody);
        res.then().statusCode(200);
    }

    @Test
    public void RegistrationInvalid(){
        Registration reg = new Registration();
        reg.setEmail("rocketraccoon99@gmail.com");

        res = req.body(reg).post("/register");
        String resBody = res.getBody().asString();

        System.out.println("Response: " + resBody);
        res.then().statusCode(400);
    }

    @Test
    public void LoginSuccessful(){
        Registration reg = new Registration();
        //reg.setUsername("Rocket_Raccoon");
        reg.setEmail("eve.holt@reqres.in");
        reg.setPassword("cityslicka");

        res = req.header("content-type", "application/json").body(reg).post("/login");
        String resBody = res.getBody().asString();

        System.out.println("Response: " + resBody);
        res.then().statusCode(200);
    }

    @Test
    public void LoginInvalid(){
        Registration reg = new Registration();
        reg.setEmail("rocketraccoon99@gmail.com");

        res = req.body(reg).post("/login");
        String resBody = res.getBody().asString();

        System.out.println("Response: " + resBody);
        res.then().statusCode(400);
    }
}
