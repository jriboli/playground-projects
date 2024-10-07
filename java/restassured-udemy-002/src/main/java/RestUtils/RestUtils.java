package RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.xml.stream.events.EndDocument;
import java.net.URI;
import java.util.Map;

public class RestUtils {

    public static Response performGet(String endpoint,
                                      Map<String, String> queryParams) {
        return RestAssured.given().log().all().
                baseUri(endpoint).
                queryParams(queryParams).
                get().
                then().log().all().extract().response();
    }

    public static Response performPost(String endPoint,
                                       String requestPayload,
                                       Map<String, String> headers) {

        return RestAssured.given().log().all().
                baseUri(endPoint).
                headers(headers).
                contentType(ContentType.JSON).
                body(requestPayload).
                post().
                then().log().all().extract().response();

    }

    public static Response performPost(String endPoint,
                                       Map<String, Object> requestPayload,
                                       Map<String, String> headers) {

        return RestAssured.given().log().all().
                baseUri(endPoint).
                headers(headers).
                contentType(ContentType.JSON).
                body(requestPayload).
                post().
                then().log().all().extract().response();

    }

    public static Response performPost(String endPoint,
                                       Object requestPayload,
                                       Map<String, String> headers) {

        return RestAssured.given().log().all().
                baseUri(endPoint).
                headers(headers).
                contentType(ContentType.JSON).
                body(requestPayload).
                post().
                then().log().all().extract().response();

    }

    public static Response performPostNoEncoding(String endPoint,
                                       Object requestPayload,
                                       Map<String, String> headers,
                                       Map<String, String> queryParams) {

        return RestAssured.given().log().all().
                urlEncodingEnabled(false).
                baseUri(endPoint).
                queryParams(queryParams).
                headers(headers).
                contentType(ContentType.JSON).
                body(requestPayload).
                post().
                then().log().all().extract().response();

    }
}
