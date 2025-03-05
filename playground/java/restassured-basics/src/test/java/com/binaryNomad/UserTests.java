package com.binaryNomad;

import com.binaryNomad.models.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests extends RestAssuredConfig {

    @Test
    public void testGetUsers() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        User[] users = response.getBody().as(User[].class);
        assertEquals(20, users.length);
    }

    @Test
    public void testGetUserById() {
        int userId = 7270226;
        given().
        when().
            get("/users/" + userId).
        then().
            statusCode(200).
            body("id", equalTo(userId));
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setGender("male");
        newUser.setStatus("active");

        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer YOUR_ACCESS_TOKEN").
                body(newUser);

        Response response = request.post("/users");
        assertEquals(201, response.statusCode());

        User createdUser = response.getBody().as(User.class);
        assertEquals(newUser.getName(), createdUser.getName());
        assertEquals(newUser.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        int userId = 1;
        User updateUser = new User();
        updateUser.setName("Jane Doe");
        updateUser.setEmail("jane.doe@example.com");
        updateUser.setGender("female");
        updateUser.setStatus("active");

        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer YOUR_ACCESS_TOKEN").
                body(updateUser);

        Response response = request.put("/users/" + userId);
        assertEquals(200, response.statusCode());

        User updatedUser = response.getBody().as(User.class);
        assertEquals(updateUser.getName(), updatedUser.getName());
        assertEquals(updateUser.getEmail(), updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;
        given().
            header("Authorization", "Bearer YOUR_ACCESS_TOKEN").
        when().
            delete("/users/" + userId).
        then().
            statusCode(204);
    }
}
