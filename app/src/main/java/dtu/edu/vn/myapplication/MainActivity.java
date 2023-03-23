package dtu.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dtu.edu.vn.myapplication.database.Product;
import dtu.edu.vn.myapplication.database.ProductOrder;

public class MainActivity extends AppCompatActivity {

    ProductOrder productOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Product product = new Product(5,
                "P4. Chorizo Pizza",
                "Xúc xích Tây Ban Nha, hành tây, ô liu, sốt cà chua, pho mai.",
                true, "https://www.pizzaexpress.vn/wp-content/uploads/2019/12/P4rs1.jpg",
                10, "Pizza");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCall = findViewById(R.id.buttonCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderProductActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        TextView textShow = findViewById(R.id.textShow);
        Button btnShow = findViewById(R.id.buttonShowProduct);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productOrder = (ProductOrder) getIntent().getSerializableExtra("productOrder");
                textShow.setText(productOrder.toString());
            }
        });
    }
}