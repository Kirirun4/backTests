package MiniMar.api;

import MiniMar.dto.GetCategoryResponse;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Path;


public interface CategoryService {
    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
