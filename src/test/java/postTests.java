import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class postTests extends AbstractTest {

    @Test
    void postTitle() {
        given()
                .spec(getRequestSpecification())
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postIngredientList() {
        given()
                .spec(getRequestSpecification())
                .formParam("ingredientList","3 oz pork shoulder")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postLanguage() {
        given()
                .spec(getRequestSpecification())
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postFull() {
        given()
                .spec(getRequestSpecification())
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
        given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .formParam("language", "en")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAccountInfoWithExternalEndpointTest(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
    }
}
