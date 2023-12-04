package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static arguments.consts.BoardEndpoints.GET_ALL_BOARDS_URL;
import static arguments.consts.BoardEndpoints.GET_BOARD_URL;
import static arguments.consts.UrlParamValues.EXISTING_BOARD_ID;
import static arguments.consts.UrlParamValues.USER_NAME;

public class GetBoardTest extends BaseTest {
    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParams("member", USER_NAME)
                .get(GET_ALL_BOARDS_URL)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/get_boards.json"));
    }

    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .queryParam("fields", "id,name,desc")
                .pathParams("id", EXISTING_BOARD_ID)
                .get(GET_BOARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("My Trello board"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/get_board.json"));
    }
}
