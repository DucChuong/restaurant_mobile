package dtu.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dtu.edu.vn.myapplication.api.ApiService;
import dtu.edu.vn.myapplication.database.Product;
import dtu.edu.vn.myapplication.database.ProductOrder;
import dtu.edu.vn.myapplication.database.ProductSize;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderProductActivity extends AppCompatActivity {
    DecimalFormat df = new DecimalFormat("#.#");
    private int productCount = 1;
    private int productSizeOrder = 2;
    private float productPrice;
    private String customerDescription;
    private Product product;

    TextView textProductPrice;
    ProductOrder productOrder;
    ArrayList<ProductSize> productSizeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_product);
        product = (Product) getIntent().getSerializableExtra("product");
        clickCallApi();

        TextView textCategory = findViewById(R.id.text_Category_Order);
        textCategory.setText(product.getCategory());

        ImageView imageProduct = findViewById(R.id.image_ProductImage_Order);
        Picasso.get().load(product.getImgSrc()).fit().centerCrop().into(imageProduct);

        TextView textProductName = findViewById(R.id.text_ProductName_Order);
        textProductName.setText(product.getName());

        textProductPrice = findViewById(R.id.text_ProductPrice_Order);
//        if(productSizeList.get(productSizeOrder) != null){
//            productPrice = Float.parseFloat(df.format(getProductPrice(productSizeOrder)));
//        }
//        textProductPrice.setText(Float.toString(productPrice));

        TextView textProductDiscount = findViewById(R.id.text_ProductDiscount_Order);
        textProductDiscount.setText(productDiscount(product.getDiscount()));

        TextView textProductDescription = findViewById(R.id.text_ProductDescription_Order);
        textProductDescription.setText(product.getDescription());


        Button btnIncrease = findViewById(R.id.button_IncreaseProduct_Order);
        Button btnDecrease = findViewById(R.id.button_DecreaseProduct_Order);
        TextView textProductCount = findViewById(R.id.text_ProductCount_Order);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productCount < 10){
                    productCount++;
                    textProductCount.setText(String.valueOf(productCount));
                    changePrice();
                    textProductPrice.setText(Float.toString(productPrice));
                }
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productCount > 1){
                    productCount--;
                    textProductCount.setText(String.valueOf(productCount));
                    changePrice();
                    textProductPrice.setText(Float.toString(productPrice));
                }
            }
        });

        RadioGroup radioGroupProductSize = findViewById(R.id.radioGroup_ProductSize_Order);
        RadioButton radioButtonSmall = findViewById(R.id.radioButton_ProductSizeS_Order);
        RadioButton radioButtonMedium = findViewById(R.id.radioButton_ProductSizeM_Order);
        RadioButton radioButtonLarge = findViewById(R.id.radioButton_ProductSizeL_Order);
        radioGroupProductSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_ProductSizeS_Order:
                        productSizeOrder = 1;
                        changePrice();
                        textProductPrice.setText(Float.toString(productPrice));
                        break;
                    case R.id.radioButton_ProductSizeM_Order:
                        productSizeOrder = 2;
                        changePrice();
                        textProductPrice.setText(Float.toString(productPrice));
                        break;
                    case R.id.radioButton_ProductSizeL_Order:
                        productSizeOrder = 3;
                        changePrice();
                        textProductPrice.setText(Float.toString(productPrice));
                        break;
                }
            }
        });

        EditText textCustomerDescription = findViewById(R.id.text_CusmoterDescription_Order);



        Button btnCancel = findViewById(R.id.button_Cancel_Order);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Button btnAdd = findViewById(R.id.button_Add_Order);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerDescription = textCustomerDescription.getText().toString();
                productOrder = new ProductOrder(product.getId(), product.getName(), productCount, productSizeOrder, customerDescription, product.getDiscount(), productPrice);
                Intent intent = new Intent(OrderProductActivity.this, MainActivity.class);
                intent.putExtra("productOrder", productOrder);
                startActivity(intent);
            }
        });
    }
    private void clickCallApi() {
        Call<ArrayList<ProductSize>> call = RetrofitClient.getInstance().getMyApi().convertProductSizeJson(product.getId());
        call .enqueue(new Callback<ArrayList<ProductSize>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductSize>> call, Response<ArrayList<ProductSize>> response) {
                productSizeList = response.body();
                textProductPrice.setText(Float.toString(productSizeList.get(1).getPrice()));
            }

            @Override
            public void onFailure(Call<ArrayList<ProductSize>> call, Throwable t) {
                Toast.makeText(OrderProductActivity.this, "Call Price API Failed", Toast.LENGTH_SHORT).show();
                Log.i("isPriceFail", "onResponse: Fail");
            }
        });
    }

    private String productDiscount(int discount){
        String textDiscount = "";
        if(discount == 0){
            return textDiscount;
        }
        else {
            textDiscount = "-" + discount + "%";
        }
        return textDiscount;
    }
    private float getProductPrice(int productSizeOrder){
        return productSizeList.get(productSizeOrder-1).getPrice();
    }
    private void changePrice(){
        productPrice = Float.parseFloat(df.format(productCount*getProductPrice(productSizeOrder)));
    }
}