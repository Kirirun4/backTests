package MiniMar;

import com.github.javafaker.Faker;
import MiniMar.api.ProductService;
import MiniMar.dto.Product;
import MiniMar.utils.RetrofitUtils;
import MiniMar.db.dao.CatMapper;
import MiniMar.db.dao.ProdMapper;
import MiniMar.db.model.Classes;
import MiniMar.db.model.ClassesExample;
import MiniMar.db.model.Products;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class CreateProductTest {
    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    int id;
    static SqlSession session;
    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        session = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }
    @BeforeEach
    void SetUP(String title, int price, String category) {
        product = new Product()
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(category);
    }
    @Test
    @Tag("Positive")
    void createProductInFoodCategoryTest() throws IOException {
        SetUP("bread", 9562, "Food");
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.code(), equalTo(201));
        assert response.body() != null;
        assertThat(response.body().getId(), notNullValue());
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        id =  response.body().getId();
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        Products selected = prodMapper.selectByPrimaryKey((long) id);
        ClassesExample example = new ClassesExample();
        example.createCriteria().andTitleLike(response.body().getCategoryTitle());
        List<Classes> list = catMapper.selectByExample(example);
        Classes classes = list.get(0);
        Long category_id = classes.getId();
        assertThat(selected.getTitle(), equalTo(response.body().getTitle()));
        assertThat(selected.getPrice(), equalTo(response.body().getPrice()));
        assertThat(selected.getCategory_id(), equalTo(category_id));
        tearDown();
        Response<Product> responseForChecking = productService.getProductById(id).execute();
        assertThat(responseForChecking.code(), equalTo(404));
    }
    @SneakyThrows
    void tearDown() {
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        Products selected = prodMapper.selectByPrimaryKey((long) id);
        prodMapper.deleteByPrimaryKey(selected.getId());
        session.commit();
    }
    @AfterAll
    static void AfterALL() {
        session.close();
    }
}
