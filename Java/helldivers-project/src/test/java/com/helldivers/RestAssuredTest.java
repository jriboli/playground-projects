package com.helldivers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredTest {

    //private String baseUri = "http://localhost:9000/api/v1";

    private static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder().setBaseUri("http://localhost:9000/api/v1")
                .build();
    }
    @Test
    public void getStratagemsTest() {

        Response response = given().spec(requestSpecification())
                .when().get("/stratagems")
                .then().assertThat().statusCode(200)
                .header("Content-Type", "application/json")
                .time(lessThan(5000L))
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test
    public void getStratagemsWithCategoryTest() {
        Response response = given().spec(requestSpecification())
                .queryParam("category", "HellpodDeployment")
                .when().get("/stratagems")
                .then().assertThat().statusCode(200)
                .header("Content-Type", "application/json")
                .time(lessThan(5000L))
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test
    public void getStratagemsWithFlagTest() {
        Response response = given().spec(requestSpecification())
                .queryParam("flag", "aerial")
                .when()
                    .get("/stratagems")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .time(lessThan(5000L))
                    .body("stratagems.findAll {it.uses > 2}.name", hasItems("Eagle Strafing Run"))
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());

        JsonPath responseBody = response.jsonPath();
        int stratagemCount = responseBody.getInt("num_of_stratagems");

        System.out.println("Count: " + stratagemCount);

        Assert.assertEquals(5, stratagemCount);
    }

    @Test
    public void getStratagemsWithFlag2Test() {
        String response = given().spec(requestSpecification())
                .queryParam("flag", "orbital")
                .when()
                    .get("/stratagems")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .header("Content-Type", "application/json")
                    .time(lessThan(5000L))
                .extract().response().asString();

        System.out.println("Response: " + response);
        List<String> names = JsonPath.from(response).getList("stratagems.findAll {it.uses < 99}.name");

        System.out.println("Stratagem Names: " + names);

    }

    @Test(dataProvider = "getFlagData")
    public void getStratagemsWithParameterizedFlagTest(String flagName) {
        System.out.println("Testing: " + flagName);

        Response response = given().spec(requestSpecification())
                .queryParam("flag", flagName)
                .when()
                .get("/stratagems")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .time(lessThan(5000L))
                .extract().response();

        JsonPath responseBody = response.jsonPath();
        int stratagemCount = responseBody.getInt("num_of_stratagems");
        System.out.println("Count: " + stratagemCount);
    }

    @DataProvider
    public static Object[][] getFlagData() {
        Object[][] data = new Object[4][1];

        data[0][0] = "hellpod";
        data[1][0] = "backpack";
        data[2][0] = "aerial";
        data[3][0] = "explosive";

        return data;
    }
}
