import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;


public class getTests extends AbstractTest {

    @Test
    void getPasta() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("number", "10")
                .queryParam("maxFat", "50")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void getItalian() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("maxCarbs", "60")
                .queryParam("maxCopper", "80")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void getFillIngredients() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("fillIngredients", "true")
                .queryParam("number", "10")
                .queryParam("maxFat", "50")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void getEggs(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeIngredients", "eggs")
                .queryParam("minCopper", "10")
                .queryParam("minVitaminD", "5")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void getAsc() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("sortDirection", "asc")
                .queryParam("minCopper", "10")
                .queryParam("maxFolate", "60")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }
}
