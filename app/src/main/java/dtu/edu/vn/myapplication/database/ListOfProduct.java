package dtu.edu.vn.myapplication.database;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.List;

public class ListOfProduct {
      Product[] arrProduct;

    public ListOfProduct(Product[] arrProduct) {
        this.arrProduct = arrProduct;
    }

    public Product[] getArrProduct() {
        return arrProduct;
    }

    public void setArrProduct(Product[] arrProduct) {
        this.arrProduct = arrProduct;
    }
}