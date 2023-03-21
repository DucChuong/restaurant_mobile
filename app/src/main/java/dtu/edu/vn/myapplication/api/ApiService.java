package dtu.edu.vn.myapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dtu.edu.vn.myapplication.database.ListOfProduct;
import dtu.edu.vn.myapplication.database.Product;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    // Link API of Product that view in Menu
    String BASE_URL = "http://192.168.1.150:5500";
    @GET("/products")
    Call<ArrayList<Product> >convertProductJson();



}
