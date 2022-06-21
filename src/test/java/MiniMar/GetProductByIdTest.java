package MiniMar;

import MiniMar.api.ProductService;
import MiniMar.dto.Product;
import MiniMar.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductByIdTest {
    static ProductService prodService;
    @BeforeAll
    static void BeforeALL() {
        prodService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    @Tag("Positive")
    void getProductById() {
        Response<Product> response = prodService.getProductById(2).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getId(), equalTo(2));
        assertThat(response.body().getTitle(), equalTo("Bread"));
        assertThat(response.body().getPrice(), equalTo(25));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
    }
    @SneakyThrows
    @Test
    @Tag("Negative")
    void GetCategoryByIdNegativeTest() {
        Response<Product> response = prodService.getProductById(0).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(404));
    }
}
