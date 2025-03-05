package org.binaryNomad.statuses;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.binaryNomad.constants.Endpoints;
import org.binaryNomad.constants.Path;
import org.binaryNomad.utility.RestUtilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TwitterWorkflowTest {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    String tweetId = "";

    @BeforeClass
    public void setup(){
        reqSpec = RestUtilities.getRequestSpecification();
        reqSpec.basePath(Path.STATUSES);

        resSpec = RestUtilities.getResponseSpecification();
    }

    @Test
    public void postTweet() {
        Response res =
                given()
                    .spec(RestUtilities.createQueryParam(reqSpec, "status", "A Test Tweet"))
                .when()
                    .post(Endpoints.STATUSES_TWEET_POST)
                .then()
                    .spec(resSpec)
                    .extract()
                    .response();

        //tweetId = res.path("id_str");
        JsonPath jsPath = RestUtilities.getJsonPath(res);
        tweetId = jsPath.get("id_str");
    }

    @Test(dependsOnMethods={"postTweet"})
    public void readTweet(){
        RestUtilities.setEndPoint(Endpoints.STATUSES_TWEET_READ_SINGLE);
        Response res = RestUtilities.getResponse(RestUtilities.createQueryParam(reqSpec, "id", tweetId), "get");
        String text = res.path("text");
        System.out.println("The tweet is: " + text);
    }

    @Test(dependsOnMethods = {"readTweet"})
    public void deleteTweet() {
        given()
            .spec(RestUtilities.createPathParam(reqSpec, "id", tweetId))
        .when()
            .post(Endpoints.STATUSES_TWEET_DESTROY)
        .then()
            .spec(resSpec)
            .extract()
            .response();
    }

}
