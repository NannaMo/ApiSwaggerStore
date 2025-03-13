package apiTestsSwagger;

import api.RestHelper;
import dto.Pet;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import routs.Endpoints;

import java.time.Instant;
import java.util.Date;

public class StoreTests {

    static final int VALID_ORDER_ID = 5;
    static final int INVALID_ORDER_ID = -5;
    static final int PET_ID = 5;
    static final int QUANTITY = 5;
    static final String STATUS = "placed";
    static final boolean COMPLETE = true;
    static final String NULL_PARAM = " ";

    private final RestHelper restHelper = new RestHelper();
    private final SoftAssertions softAssertions = new SoftAssertions();

    @Test(priority = 1)
    public void testPostStoreOrder() {
        Pet createdPet = Pet.builder()
                .id(VALID_ORDER_ID)
                .petId(PET_ID)
                .quantity(QUANTITY)
                .orderDate(Date.from(Instant.now()))
                .status(STATUS)
                .complete(COMPLETE)
                .build();

        Response postResponse = restHelper.sendPostRequest(Endpoints.STORE_ORDER, createdPet);

        softAssertions.assertThat(postResponse.getStatusCode())
                .as("Assert status-code is 200")
                .isEqualTo(200);

        softAssertions.assertThat(postResponse.getContentType())
                .as("Assert content-type is application/json")
                .containsIgnoringCase("application/json");

        softAssertions.assertThat(postResponse.jsonPath().getInt("petId"))
                .as("Assert petId")
                .isEqualTo(PET_ID);

        softAssertions.assertThat(postResponse.jsonPath().getString("status"))
                .as("Assert status")
                .isEqualTo(STATUS);

        softAssertions.assertAll();
    }

    @Test(priority = 2)
    public void testGetStoreOrder() {
        Response getResponse = restHelper.sendGetRequestWithParam(Endpoints.STORE_ORDER_ID, VALID_ORDER_ID);

        softAssertions.assertThat(getResponse.getStatusCode())
                .as("Assert status-code is 200")
                .isEqualTo(200);

        softAssertions.assertThat(getResponse.getContentType())
                .as("Assert content-type is application/json")
                .containsIgnoringCase("application/json");

        softAssertions.assertThat(getResponse.jsonPath().getInt("id"))
                .as("Assert orderId")
                .isEqualTo(VALID_ORDER_ID);

        softAssertions.assertThat(getResponse.jsonPath().getInt("petId"))
                .as("Assert petId")
                .isNotNull();

        softAssertions.assertAll();
    }

    @Test(priority = 3)
    public void testDeleteStoreOrder() {
        Response deleteResponse = restHelper.sendDeleteRequest(Endpoints.STORE_ORDER_ID, VALID_ORDER_ID);

        softAssertions.assertThat(deleteResponse.getStatusCode())
                .as("Assert status-code is 200")
                .isEqualTo(200);

        Response getResponseAfterDelete = restHelper.sendGetRequestWithParam(Endpoints.STORE_ORDER_ID, VALID_ORDER_ID);

        softAssertions.assertThat(getResponseAfterDelete.getStatusCode())
                .as("Assert status-code is 404")
                .isEqualTo(404);

        softAssertions.assertAll();
    }

    @Test
    public void testGetStoreInventory() {
        Response getResponse = restHelper.sendGetRequest(Endpoints.STORE_INVENTORY);

        softAssertions.assertThat(getResponse.body())
                .as("Assert body is not null")
                .isNotNull();

        softAssertions.assertAll();
    }

    @Test
    public void testPostStoreOrderWithNoBody() {
        Response postResponse = restHelper.sendPostRequest(Endpoints.STORE_ORDER, NULL_PARAM);
        softAssertions.assertThat(postResponse.getStatusCode())
                .as("Assert status-code is 400")
                .isEqualTo(400);

        softAssertions.assertAll();
    }

    @Test
    public void testGetStoreOrderWithInvalidId() {
        Response getResponse = restHelper.sendGetRequestWithParam(Endpoints.STORE_ORDER_ID, INVALID_ORDER_ID);

        softAssertions.assertThat(getResponse.getStatusCode())
                .as("Assert status-code is 404")
                .isEqualTo(404);

        softAssertions.assertAll();
    }

    @Test
    public void testDeleteStoreOrderWithInvalidId() {
        Response deleteResponse = restHelper.sendDeleteRequest(Endpoints.STORE_ORDER_ID, INVALID_ORDER_ID);

        softAssertions.assertThat(deleteResponse.getStatusCode())
                .as("Assert status-code is 404")
                .isEqualTo(404);

        softAssertions.assertAll();
    }

    @Test
    public void testDeleteStoreOrderWithNoId() {
        Response deleteResponse = restHelper.sendDeleteRequest(Endpoints.STORE_ORDER_ID, null);

        softAssertions.assertThat(deleteResponse.getStatusCode())
                .as("Assert status-code is 404")
                .isEqualTo(404);

        softAssertions.assertAll();
    }
}