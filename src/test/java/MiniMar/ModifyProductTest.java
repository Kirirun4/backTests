package MiniMar;

import MiniMar.api.ProductService;
import MiniMar.dto.Product;
import MiniMar.utils.RetrofitUtils;
import MiniMar.db.dao.CatMapper;
import MiniMar.db.dao.ProdMapper;
import MiniMar.db.model.Classes;
import MiniMar.db.model.ClassesExample;
import MiniMar.db.model.Products;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ModifyProductTest {
    static ProductService prodService;
    Product prod = null;
    int id = 1;
    static SqlSession session;
    String title;
    int price;
    String category;
    String titleBeforeChanges;
    int priceBeforeChanges;
    String categoryBeforeChanges;

    @BeforeAll
    static void beforeAll() throws IOException {
        prodService = RetrofitUtils.getRetrofit().create(ProductService.class);
        session = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    void SetUP() {
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
        titleBeforeChanges = response.body().getTitle();
        priceBeforeChanges = response.body().getPrice();
        categoryBeforeChanges = response.body().getCategoryTitle();
        title = "computer";
        price = 5000;
        category = "Electronic";
        SetUP();
        response = prodService.modifyProduct(prod).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getTitle() != titleBeforeChanges, is (true));
        assertThat(response.body().getPrice() != priceBeforeChanges, is (true));
        assertThat(response.body().getCategoryTitle() != categoryBeforeChanges, is (true));
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        Products selected = prodMapper.selectByPrimaryKey((long) response.body().getId());
        ClassesExample example = new ClassesExample();
        example.createCriteria().andTitleLike(response.body().getCategoryTitle());
        List<Classes> list = catMapper.selectByExample(example);
        Classes classes = list.get(0);
        Long category_id = classes.getId();
        assertThat(selected.getTitle(), equalTo(title));
        assertThat(selected.getPrice(), equalTo(price));
        assertThat(selected.getCategory_id(), equalTo(category_id));
        tearDown();
    }
    @SneakyThrows
    void tearDown() {
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        Products selected_p = prodMapper.selectByPrimaryKey((long) id);
        ClassesExample example = new ClassesExample();
        example.createCriteria().andTitleLike(categoryBeforeChanges);
        List<Classes> list = catMapper.selectByExample(example);
        Classes classes = list.get(0);
        Long category_id = classes.getId();
        selected_p.setTitle(titleBeforeChanges);
        selected_p.setPrice(priceBeforeChanges);
        selected_p.setCategory_id(category_id);
        prodMapper.updateByPrimaryKey(selected_p);
        session.commit();
        assertThat(selected_p.getTitle(), equalTo(titleBeforeChanges));
        assertThat(selected_p.getPrice(), equalTo(priceBeforeChanges));
        assertThat(selected_p.getCategory_id(), equalTo(category_id));
    }
    @AfterAll
    static void AfterALL() {
        session.close();
    }
}
