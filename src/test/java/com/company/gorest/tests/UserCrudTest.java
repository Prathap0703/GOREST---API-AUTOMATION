package com.company.gorest.tests;

import com.company.gorest.base.BaseTest;
import com.company.gorest.client.UserApiClient;
import com.company.gorest.models.User;
import com.company.gorest.utils.TestDataUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCrudTest extends BaseTest {

    @Test
    public void testUserCrudFlow() {

        System.out.println("STARTING CRUD TEST");

        UserApiClient userApi = new UserApiClient(request);

        // CREATE USER
        User user = new User();
        user.setName(TestDataUtil.getRandomName());
        user.setEmail(TestDataUtil.getRandomEmail());
        user.setGender("male");
        user.setStatus("active");

        System.out.println("Creating user...");
        Response createResponse = userApi.createUser(user);
        System.out.println("Create Response: " + createResponse.asPrettyString());

        Assert.assertEquals(createResponse.getStatusCode(), 201);

        int userId = createResponse.jsonPath().getInt("id");
        System.out.println("Created User ID: " + userId);

        // GET USER
        System.out.println("Fetching created user...");
        Response getResponse = userApi.getUser(userId);
        System.out.println("Get Response: " + getResponse.asPrettyString());

        Assert.assertEquals(getResponse.getStatusCode(), 200);

        // UPDATE USER
        System.out.println("Updating user...");
        user.setName("UpdatedName");

        Response updateResponse = userApi.updateUser(userId, user);
        System.out.println("Update Response: " + updateResponse.asPrettyString());

        Assert.assertEquals(updateResponse.getStatusCode(), 200);

        // DELETE USER
        System.out.println("Deleting user...");
        Response deleteResponse = userApi.deleteUser(userId);
        System.out.println("Delete Status Code: " + deleteResponse.getStatusCode());

        Assert.assertEquals(deleteResponse.getStatusCode(), 204);

        // VERIFY DELETION
        System.out.println("Verifying deletion...");
        Response deletedUserResponse = userApi.getUser(userId);
        System.out.println("Verify Delete Response Status: " + deletedUserResponse.getStatusCode());

        Assert.assertEquals(deletedUserResponse.getStatusCode(), 404);

        System.out.println("CRUD TEST COMPLETED SUCCESSFULLY");
    }

    @Test
    public void testGetUserWithInvalidId() {

        System.out.println("STARTING NEGATIVE TEST");

        UserApiClient userApi = new UserApiClient(request);

        Response response = userApi.getUser(99999999);

        System.out.println("Invalid ID Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 404);

        System.out.println("NEGATIVE TEST COMPLETED");
    }
}
