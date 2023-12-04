package test.get;

import arguments.holders.AuthorizationHolder;
import arguments.holders.ValidationHolder;
import arguments.providers.BoardAuthorizationProvider;
import arguments.providers.BoardValidationProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import static arguments.consts.BoardEndpoints.GET_BOARD_URL;
import static arguments.consts.UrlParamValues.ANOTHER_USER_QUERY_PARAM;
import static arguments.consts.UrlParamValues.EXISTING_BOARD_ID;

public class GetBoardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(BoardValidationProvider.class)
    public void checkGetBoardWithInvalidId(ValidationHolder validationHolder) {
        Response response = requestWithAuth()
                .pathParams(validationHolder.getPathParam())
                .get(GET_BOARD_URL);
        response.then().statusCode(validationHolder.getCode());
        Assertions.assertEquals(validationHolder.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(BoardAuthorizationProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthorizationHolder authorizationHolder) {
        Response response = requestWithOutAuth()
                .queryParams(authorizationHolder.getAuthParam())
                .pathParams("id", EXISTING_BOARD_ID)
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(authorizationHolder.getMessageError(), response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = requestWithOutAuth()
                .queryParams(ANOTHER_USER_QUERY_PARAM)
                .pathParams("id", EXISTING_BOARD_ID)
                .get(GET_BOARD_URL);
        response.then().statusCode(401);
        Assertions.assertEquals("invalid app token", response.body().asString());
    }
}
