package test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static arguments.consts.UrlParamValues.*;

public class BaseTest {
    @BeforeAll
    public static void setBaseURL() {

        RestAssured.baseURI = "http://api.trello.com";
    }

    public RequestSpecification requestWithAuth() {
        return requestWithOutAuth().queryParams(AUTH_QUERY_PARAM);
    }

    public RequestSpecification requestWithOutAuth() {
        return RestAssured.given();
    }
}
