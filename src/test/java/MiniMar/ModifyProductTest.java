package MiniMar;

import MiniMar.api.ProductService;
import MiniMar.dto.Product;
import MiniMar.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest {
    static ProductService prodService;
    Product prod = null;
    int id = 1;


    @BeforeAll
    static void beforeAll() {
        prodService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    void setUp(String title, int price, String category) {
        prod = new Product()
                .withId(id)
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(category);
    }

    @Test
    @Tag("Positive")
    void modifyProduct() throws IOException {
        Response<Product> response = prodService.getProductById(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assert response.body() != null;
        String titleBeforeChanges = response.body().getTitle();
        int priceBeforeChanges = response.body().getPrice();
        String categoryBeforeChanges = response.body().getCategoryTitle();
        setUp("Laptop", 45000, "Electronic");
        response = prodService.modifyProduct(prod).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getTitle() != titleBeforeChanges, is (true));
        assertThat(response.body().getPrice() != priceBeforeChanges, is (true));
        assertThat(response.body().getCategoryTitle() != categoryBeforeChanges, is (true));

        tearDown();
    }
    @SneakyThrows
    void tearDown() {
        Response<Product> response = prodService.modifyProduct(prod).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
