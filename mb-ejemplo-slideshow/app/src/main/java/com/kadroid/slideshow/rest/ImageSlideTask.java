package com.kadroid.slideshow.rest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.kadroid.slideshow.model.SlideItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalu on 07/06/2015.
 */
public class ImageSlideTask extends AsyncTask<String, Void, List<SlideItem>> {

    List<SlideItem> slideItems;
    Context context;
    Throwable error;
    private ImageSlidePost imageSlidePost;
    String mensaje;

    public ImageSlideTask(Activity context, ImageSlidePost imageSlidePost) {
        this.context = context;
        this.imageSlidePost=imageSlidePost;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<SlideItem> doInBackground(String... urls) {
        try {
            SlideItem slideItem1 = new SlideItem(1,"imagen_1","http://images.alphacoders.com/598/598399.jpg");
            SlideItem slideItem2 = new SlideItem(2,"imagen_2","http://images2.alphacoders.com/598/598397.jpg");
            SlideItem slideItem3 = new SlideItem(3,"imagen_3","http://images.alphacoders.com/512/512211.jpg");
            SlideItem slideItem4 = new SlideItem(4,"imagen_4","http://images5.alphacoders.com/599/599101.jpg");
            SlideItem slideItem5 = new SlideItem(5,"imagen_5","http://images3.alphacoders.com/597/597555.jpg");
            slideItems = new ArrayList<SlideItem>();
            slideItems.add(slideItem1);
            slideItems.add(slideItem2);
            slideItems.add(slideItem3);
            slideItems.add(slideItem4);
            slideItems.add(slideItem5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return slideItems;
    }

    @Override
    protected void onPostExecute(List<SlideItem> result) {
        super.onPostExecute(result);

        slideItems = result;
            if (result != null) {
                mensaje ="ok";
                imageSlidePost.fin_conexion_slide(true, mensaje,slideItems);

            } else {
                mensaje ="Hubo un error";
                imageSlidePost.fin_conexion_slide(false, mensaje,slideItems);
            }


    }
}