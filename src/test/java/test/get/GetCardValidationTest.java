package test.get;

import arguments.holders.AuthorizationHolder;
import arguments.holders.ValidationHolder;
import arguments.providers.CardAuthorizationProvider;
import arguments.providers.CardValidationProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class GetCardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(CardValidationProvider.class)
    public void checkGetCardWithInvalidId(ValidationHolder validationHolder) {
        Response response = requestWithAuth()
                .pathParams(validationHolder.getPathParam())
                .get("/1/cards/{card_id}");
        response.then()
                .statusCode(validationHolder.getCode());
        Assertions.assertEquals(validationHolder.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardAuthorizationProvider.class)
    public void checkGetCardWithInvalidAuth(AuthorizationHolder authorizationHolder) {
        Response response = requestWithOutAuth()
                .queryParams(authorizationHolder.getAuthParam())
                .pathParams("card_id", "6560f094e2b0c1cfc50eecc7")
                .get("/1/cards/{card_id}");
        response.then()
                .statusCode(401);
        Assertions.assertEquals(authorizationHolder.getMessageError(), response.body().asString());

    }

    @Test
    public void checkGetCardWitAnotherUserCredential() {
        Response response = requestWithOutAuth()
                .queryParams(Map.of("key", "8f28312056ee66ac3a48b44ee77985f4",
                        "token", "ATTA69f6133945e1e80394ba"))
                .pathParams("card_id", "6560f094e2b0c1cfc50eecc7")
                .get("/1/cards/{card_id}");
        response.then().statusCode(401);
        Assertions.assertEquals("invalid app token", response.body().asString());
    }
}
