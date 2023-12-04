package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetCardTest extends BaseTest {
    @Test
    public void checkGetCards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParams("list_id", "655fa3fe0d66a5c253978241")
                .get("/1/lists/{list_id}/cards")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/get_cards.json"));
    }

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParams("card_id", "6560f094e2b0c1cfc50eecc7")
                .get("/1/cards/{card_id}")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/get_card.json"));
    }
}
