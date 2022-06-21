package Spoonacular;

import Spoonacular.com.example.dto.AppMealResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class postTests extends AbstractTest {

    @Test
    void postTitle() {
        given()
                .spec(requestSpecification)
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postIngredientList() {
        given()
                .spec(requestSpecification)
                .formParam("ingredientList","3 oz pork shoulder")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postLanguage() {
        given()
                .spec(requestSpecification)
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postFull() {
        given()
                .spec(requestSpecification)
                .formParam("title","Pork roast with green beans")
                .formParam("ingredientList","3 oz pork shoulder")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void testBudrger(){
        given()
                .spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .formParam("language", "en")
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postShoppingList() {
        String id = given()
                .spec(requestSpecification)
                .queryParam("hash","43f7c5c67539161922fa9094c9aef27f4861a14c")
                .pathParam("username", "kirill4")
                .when()
                .post(getBaseUrl()+"mealplanner/{username}/items")
                .prettyPeek()
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(AppMealResponse.class)
                .getId();

        given()
                .spec(requestSpecification)
                .queryParam("hash","43f7c5c67539161922fa9094c9aef27f4861a14c")
                .pathParam("username", "kirill4")
                .delete(getBaseUrl()+"mealplanner/{username}/items/" + id)
                .then()
                .spec(responseSpecification);
    }
}
