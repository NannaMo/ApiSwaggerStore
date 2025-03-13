package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestHelper {
    protected static final String URL = "https://petstore.swagger.io/v2";

    @Step("POST {relativeUrl}")
    public <T> Response sendPostRequest(String relativeUrl, T requestBody) {
        return RestAssured.given()
                .spec(requestSpecification())
                .log().all()
                .body(requestBody)
                .post(relativeUrl)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("GET {relativeUrl}")
    public <T> Response sendGetRequestWithParam(String relativeUrl, T resourceId) {
        return RestAssured.given().baseUri(URL)
                .log().all()
                .get(relativeUrl + resourceId)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("GET {relativeUrl}")
    public <T> Response sendGetRequest(String relativeUrl) {
        return RestAssured.given().baseUri(URL)
                .log().all()
                .get(relativeUrl)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("DELETE {relativeUrl}")
    public <T> Response sendDeleteRequest(String relativeUrl, T resourceId) {
        return RestAssured.given().baseUri(URL)
                .log().all()
                .delete(relativeUrl + resourceId)
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected RequestSpecification requestSpecification() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri(URL)
                .contentType(ContentType.JSON);
    }
}