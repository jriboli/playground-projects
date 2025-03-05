package org.binaryNomad.statuses;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.binaryNomad.constants.Endpoints;
import org.binaryNomad.constants.Path;
import org.binaryNomad.utility.RestUtilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class UserTimelineTest {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void setup() {
        reqSpec = RestUtilities.getRequestSpecification();
        reqSpec.queryParam("user_id", "naroken");
        reqSpec.basePath(Path.STATUSES);

        resSpec = RestUtilities.getResponseSpecification();
    }

    @Test
    public void readTweets() {
        given()
            .spec(RestUtilities.createQueryParam(reqSpec, "count", "1"))
        .when()
            .get(Endpoints.STATUSES_USER_TIMELINE)
        .then()
            .spec(resSpec)
            .body("user.screen_name", hasItem("naroken"));

    }

    @Test
    public void readTweets2() {
        RestUtilities.setEndPoint(Endpoints.STATUSES_USER_TIMELINE);
        Response res = RestUtilities.getResponse(
                RestUtilities.createQueryParam(reqSpec, "count", "2"), "get");
        ArrayList<String> screenNameList = res.path("user.screen_name");
        System.out.println("Read Tweets 2 Method");
        Assert.assertTrue(screenNameList.contains("apiautomation"));
    }
}
