package apis;

import RestUtils.RestUtils;
import factory.UserFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.bookstore.User;

import java.util.HashMap;

import static io.restassured.path.json.JsonPath.from;

public class BookStoreAPI {

    public static String baseEndpoint = "https://bookstore.toolsqa.com";

    // USER related methods
    public Response generateUser(User user) {
        String endpoint = baseEndpoint + "/account/v1/user";
        Response res = RestUtils.performPost(endpoint, user, new HashMap<>());

        // Associate UUID to user object
        user.setUuid(from(res.asString()).getString("userID"));

        return res;
    }

    public Response generateUser() {
        User randomUser = UserFactory.createRandomUser();
        return generateUser(randomUser);
    }

    public Response authenticateUser(User user) {
        String endpoint = baseEndpoint + "/account/v1/authorized";
        return RestUtils.performPost(endpoint, user, new HashMap<>());
    }

    public Response generateUserToken(User user) {
        String endpoint = baseEndpoint + "/account/v1/generateToken";
        return RestUtils.performPost(endpoint, user, new HashMap<>());
    }

    public Response retrieveUserDetails(User user) {
        // REQUIRED AUTHENTICATION
        HashMap<String, String> headers = addAuthorizationToken(user);
        String endpoint = baseEndpoint + "/account/v1/user/" + user.getUuid();
        return RestUtils.performGet(endpoint, new HashMap<>(), headers);
    }

    public Response removeUser(User user) {
        String endpoint = baseEndpoint + "/account/v1/user/" + user.getUuid();
        return RestUtils.performDelete(endpoint, new HashMap<>());
    }


    // Book related methods
    public Response retrieveAllBooks() {
        String endpoint = baseEndpoint + "/bookstore/v1/books";
        return RestUtils.performGet(endpoint, new HashMap<>(), new HashMap<>());
    }

    public Response retrieveBook(String isbn) {
        String endpoint = baseEndpoint + "/bookstore/v1/book";
        return RestUtils.performGet(endpoint, new HashMap<>(), new HashMap<>());
    }

    public Response associateBookToUser(String bookIsbn, User user) {
        // REQUIRED AUTHENTICATION
        HashMap<String, String> headers = addAuthorizationToken(user);
        String endpoint = baseEndpoint + "/bookstore/v1/books";
        return RestUtils.performPost(endpoint, user, headers);
    }


    // Private Methods
    private HashMap<String, String> addAuthorizationToken(User user) {
        Response res = generateUserToken(user);
        String resBody = res.asString();
        String token = new JsonPath(resBody).getString("token");

        HashMap<String, String> results = new HashMap<>();
        results.put("authorization", token);

        return results;
    }
}
