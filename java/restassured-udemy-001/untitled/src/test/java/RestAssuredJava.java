import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class RestAssuredJava {

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
    public void getUserData(){
        res = req.request(Method.GET, "/users");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);
    }

    @Test
    public void testJSONReadingData(){
        res = req.request(Method.GET, "/users");

        JsonPath path = res.jsonPath();
        System.out.println("Result: " + path.get("data.id"));
    }

    @Test
    public void testResponseStatus(){
        res = req.request(Method.GET, "/users");

        int statusCode = res.getStatusCode();
        System.out.println("StatusCode: " + statusCode);

        Assert.assertEquals(statusCode, 200, "Correct Status Code responded.");
    }

    @Test
    public void testResponseStatus2(){
        res = req.request(Method.GET, "/users");

//        ValidatableResponse dRes = res.then();
//        dRes.statusCode(200);

        res.then().statusCode(200);
    }

    @Test
    public void testResponseHeader(){
        res = req.request(Method.GET, "/users");

        String contentType = res.contentType();
        System.out.println("ContentType: " + contentType);
    }

    @Test
    public void testAllResponseHeader(){
        res = req.request(Method.GET, "/users");

        Headers allHeaders = res.headers();
        for(Header head : allHeaders) {
            System.out.println("Key: " + head.getName() + "\tValue: " + head.getValue());
        }
    }

    @Test
    public void testResponseHeader2(){
        res = req.request(Method.GET, "/users");

        res.then().header("content-type", "application/json; charset=utf-8");
    }

    @Test
    public void testQueryParameters() {
        res = req.queryParam("page", 2).get("/users");
    }
}
