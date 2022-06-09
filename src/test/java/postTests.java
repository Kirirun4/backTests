import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;


public class postTests extends AbstractTest {

    @Test
    void postTitle() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void postIngredientList() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList","3 oz pork shoulder")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void postLanguage() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void postFull() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .formParam("ingredientList","3 oz pork shoulder")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void postShoppingList() {
        String id = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash","43f7c5c67539161922fa9094c9aef27f4861a14c")
                .pathParam("username", "kirill4")
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"mealplanner/{username}/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash","43f7c5c67539161922fa9094c9aef27f4861a14c")
                .pathParam("username", "kirill4")
                .delete(getBaseUrl()+"mealplanner/{username}/items/" + id)
                .then()
                .statusCode(200);
    }
}
