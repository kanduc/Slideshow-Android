package com.kadroid.slideshow.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kadroid.mb_ejemplo_slideshow.R;
import com.kadroid.slideshow.fragment.MainFragment;
import com.kadroid.slideshow.model.SlideItem;
import com.kadroid.slideshow.util.BitmapManager;

import java.util.List;

/**
 * Created by Kalu on 07/06/2015.
 */
public class ImageSlideAdapter extends PagerAdapter {
    FragmentActivity activity;
    List<SlideItem> slideItems;
    MainFragment mainFragment;

    public ImageSlideAdapter(FragmentActivity activity, List<SlideItem> slideItems,
                             MainFragment mainFragment) {
        this.activity = activity;
        this.mainFragment = mainFragment;
        this.slideItems = slideItems;

    }

    @Override
    public int getCount() {
        return slideItems.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager_imagen, container, false);

        ImageView mImageView = (ImageView) view
                .findViewById(R.id.image_display);


        mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Seleccionó la imagen "+((SlideItem) slideItems.get(position)).getId(), Toast.LENGTH_LONG).show();
            }
        });


        BitmapManager.getInstance().loadBitmap(((SlideItem) slideItems.get(position)).getUrl(), mImageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
