package dtu.edu.vn.myapplication.menuslide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import dtu.edu.vn.myapplication.R;

public class SlideMenuViewAdapter extends PagerAdapter {
    private List<SlideMenu> mListPhoto;

    public SlideMenuViewAdapter(List<SlideMenu> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.photo_menu_slide,container,false);
        ImageView imgPhoto =view.findViewById(R.id.img_photo);

        SlideMenu slidemenu = mListPhoto.get(position);
        imgPhoto.setImageResource(slidemenu.getResourceId());

        // add view
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(mListPhoto!=null){
            return mListPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }
}
