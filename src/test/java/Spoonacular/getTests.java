package Spoonacular;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class getTests extends AbstractTest {

    @Test
    void getPasta() {
        given()
                .spec(requestSpecification)
                .queryParam("query", "pasta")
                .queryParam("number", "10")
                .queryParam("maxFat", "50")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getItalian() {
        given()
                .spec(requestSpecification)
                .queryParam("cuisine", "italian")
                .queryParam("maxCarbs", "60")
                .queryParam("maxCopper", "80")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getFillIngredients() {
        given()
                .spec(requestSpecification)
                .queryParam("fillIngredients", "true")
                .queryParam("number", "10")
                .queryParam("maxFat", "50")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getEggs(){
        given()
                .spec(requestSpecification)
                .queryParam("excludeIngredients", "eggs")
                .queryParam("minCopper", "10")
                .queryParam("minVitaminD", "5")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAsc() {
        given()
                .spec(requestSpecification)
                .queryParam("sortDirection", "asc")
                .queryParam("minCopper", "10")
                .queryParam("maxFolate", "60")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
}
