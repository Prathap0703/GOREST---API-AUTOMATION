package com.company.gorest.client;

import com.company.gorest.models.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApiClient {

    private RequestSpecification request;

    public UserApiClient(RequestSpecification request) {
        this.request = request;
    }

    // Create User
    public Response createUser(User user) {
        return request
                .body(user)
                .when()
                .post("/public/v2/users")
                .then()
                .extract().response();
    }

    // Get User
    public Response getUser(int userId) {
        return request
                .when()
                .get("/public/v2/users/" + userId)
                .then()
                .extract().response();
    }

    // Update User
    public Response updateUser(int userId, User user) {
        return request
                .body(user)
                .when()
                .put("/public/v2/users/" + userId)
                .then()
                .extract().response();
    }

    // Delete User
    public Response deleteUser(int userId) {
        return request
                .when()
                .delete("/public/v2/users/" + userId)
                .then()
                .extract().response();
    }
}
