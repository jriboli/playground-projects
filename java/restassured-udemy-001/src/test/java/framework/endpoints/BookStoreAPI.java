package framework.endpoints;

import RestUtils.RestUtils;
import io.restassured.response.Response;
import framework.playloads.bookstore.User;

import java.util.HashMap;

import static io.restassured.path.json.JsonPath.from;

public class BookStoreAPI {

    public static String baseEndpoint = "https://bookstore.toolsqa.com";
    private AccountAPI accountAPI = new AccountAPI();

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
        HashMap<String, String> headers = accountAPI.addAuthorizationToken(user);
        String endpoint = baseEndpoint + "/bookstore/v1/books";
        return RestUtils.performPost(endpoint, user, headers);
    }
}
