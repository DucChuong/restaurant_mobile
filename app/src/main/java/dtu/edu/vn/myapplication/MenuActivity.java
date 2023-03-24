package dtu.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dtu.edu.vn.myapplication.api.RetrofitClient;
import dtu.edu.vn.myapplication.database.Product;
import dtu.edu.vn.myapplication.menulistproduct.MenuListAdapter;
import dtu.edu.vn.myapplication.menuslide.SlideMenu;
import dtu.edu.vn.myapplication.menuslide.SlideMenuViewAdapter;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    Button btn_Pizza;
    Button btn_Pasta;
    Button btn_Salad;
    Button btn_Bbq;
    Button btn_Drink;
    private List<SlideMenu> mListPhoto;
    private ArrayList<Product> arrProduct;
    private ArrayList<Product> arrProductPizza = new ArrayList<Product>();
    private ArrayList<Product> arrProductSalad = new ArrayList<Product>();
    private ArrayList<Product> arrProductBbq = new ArrayList<Product>();
    private ArrayList<Product> arrProductPasta = new ArrayList<Product>();
    private ArrayList<Product> arrProductDrink= new ArrayList<Product>();
    MenuListAdapter menuAdapter;
    ListView listViewMenu;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(mViewPager.getCurrentItem() == mListPhoto.size()-1){
                mViewPager.setCurrentItem(0);
            }else mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        handleSileMenu();
        callApi();
        handleViewProduct();




    }

    private void handleViewProduct() {

        btn_Pizza = findViewById(R.id.btn_pizza);
        btn_Pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCategoryProduct(arrProductPizza);

            }


        });
        btn_Bbq = findViewById(R.id.btn_bbq);
        btn_Bbq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCategoryProduct(arrProductBbq);
            }
        });
        btn_Drink = findViewById(R.id.btn_drink);
        btn_Drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCategoryProduct(arrProductDrink);
            }
        });
        btn_Pasta = findViewById(R.id.btn_spaghetti);
        btn_Pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCategoryProduct(arrProductPasta);
            }
        });
        btn_Salad = findViewById(R.id.btn_salad);
        btn_Salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCategoryProduct(arrProductSalad);
            }
        });



    }
    private void handleCategoryProduct(ArrayList<Product> Arrcategory) {
        menuAdapter = new MenuListAdapter(MenuActivity.this,R.layout.cell_layout_product,Arrcategory);
        listViewMenu.setAdapter(menuAdapter);
    }

    private void callApi() {
        Call<ArrayList<Product> > call = RetrofitClient.getInstance().getMyApi().convertProductJson();

        call.enqueue(new Callback<ArrayList<Product>>() {

            @Override
            public void onResponse(Call<ArrayList<Product> > call, Response<ArrayList<Product> > response) {
                Toast.makeText(MenuActivity.this,"Success",Toast.LENGTH_LONG).show();

                arrProduct = response.body();


                for (int i = 0; i < arrProduct.size(); i++) {
                    switch (arrProduct.get(i).getCategory()){
                        case "pizza":
                            arrProductPizza.add(arrProduct.get(i));
                            break;
                        case "bbq":
                            arrProductBbq.add(arrProduct.get(i));
                            break;
                        case "salad":
                            arrProductSalad.add(arrProduct.get(i));
                            break;
                        case "drink":
                            arrProductDrink.add(arrProduct.get(i));
                            break;
                        default:
                            arrProductPasta.add(arrProduct.get(i));
                            break;
                    }


                }
                listViewMenu = findViewById(R.id.listProduct);
                menuAdapter = new MenuListAdapter(MenuActivity.this,R.layout.cell_layout_product,arrProductPizza);
                listViewMenu.setAdapter(menuAdapter);

                listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Product productOrder = arrProductPizza.get(i);
                        Intent intent = new Intent(MenuActivity.this, OrderProductActivity.class);
                        intent.putExtra("product", productOrder);
                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onFailure(Call<ArrayList<Product> > call, Throwable t) {
                Toast.makeText(MenuActivity.this,"Fail",Toast.LENGTH_LONG).show();
                Log.i("fail","f");
            }
        });
    }

    private void handleSileMenu() {
        mViewPager =findViewById(R.id.view_pager_menu_slide);
        mCircleIndicator = findViewById(R.id.circle_center_menu_slide);
        mListPhoto = getListPhoto();
        SlideMenuViewAdapter adapterSlideMenu = new SlideMenuViewAdapter(mListPhoto);
        mViewPager.setAdapter(adapterSlideMenu);
        mCircleIndicator.setViewPager(mViewPager);
        mHandler.postDelayed(mRunnable, 3000);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private List<SlideMenu> getListPhoto(){
        List<SlideMenu> list= new ArrayList<>();
        list.add(new SlideMenu(R.drawable.img_1));
        list.add(new SlideMenu(R.drawable.img_2));
        list.add(new SlideMenu(R.drawable.img_3));
        return list;

    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,3000);
    }
}