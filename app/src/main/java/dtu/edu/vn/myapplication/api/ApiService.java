package dtu.edu.vn.myapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dtu.edu.vn.myapplication.database.Product;
import dtu.edu.vn.myapplication.database.ProductSize;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  ApiService {

    String BASE_URL = "http://192.168.1.150:5500/";
    @GET("productSize/{product_id}")
    Call<ArrayList<ProductSize>> convertProductSizeJson(@Path("product_id") int product_id);
    @GET("products")
    Call <ArrayList<Product>> convertProductJson();

//    @Query("id") int id,
//    @Query("name") String name,
//    @Query("description") String description,
//    @Query("status") boolean status,
//    @Query("imgSrc") String imgSrc,
//    @Query("discount") int discount,
//    @Query("category") String category
}
