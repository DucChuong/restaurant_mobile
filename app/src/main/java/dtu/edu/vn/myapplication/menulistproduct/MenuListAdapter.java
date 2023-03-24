package dtu.edu.vn.myapplication.menulistproduct;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dtu.edu.vn.myapplication.OrderProductActivity;
import dtu.edu.vn.myapplication.R;
import dtu.edu.vn.myapplication.database.Product;

public class MenuListAdapter extends ArrayAdapter<Product> {
    Context context;
    int layout;
   ArrayList<Product> productList;

    public MenuListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.productList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View cellView = convertView;
        if(cellView == null) cellView = LayoutInflater.from(context).inflate(this.layout,parent, false);
        final Product p =productList.get(position);
        TextView nameProduct = cellView.findViewById(R.id.name_product);
        nameProduct.setText(p.getName());
        TextView descProduct = cellView.findViewById(R.id.desc_product);
        descProduct.setText(p.getDescription());
        ImageView imgProduct = cellView.findViewById(R.id.img_product);
        Picasso.get().load(p.getImgSrc()).resize(250, 250)
                .centerCrop().into(imgProduct);
        return cellView;
    }
}
