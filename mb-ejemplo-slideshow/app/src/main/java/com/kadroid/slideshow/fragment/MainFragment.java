package com.kadroid.slideshow.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kadroid.mb_ejemplo_slideshow.R;
import com.kadroid.slideshow.adapter.ImageSlideAdapter;
import com.kadroid.slideshow.model.SlideItem;
import com.kadroid.slideshow.rest.ImageSlidePost;
import com.kadroid.slideshow.rest.ImageSlideTask;

import java.util.List;

/**
 * Created by Kalu on 07/06/2015.
 */
public class MainFragment extends Fragment implements ImageSlidePost {



    public static final String FRAGMENT_ID = "home_fragment";

    private static final long ANIMACION_VIEWPAGER_DELAY = 2500;
    private static final long ANIMACION_VIEWPAGER_DELAY_USER_VIEW = 5000;

    private ViewPager viewPager_slide;
    TextView txv_nombre_imagen;

    List<SlideItem> lista_slideItems;
    ImageSlideTask task;
    boolean detenerSlider = false;

    private Runnable animacionViewPager;
    private Handler handler;

    FragmentActivity activity;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findViewById(view);

        viewPager_slide.setOnPageChangeListener(new PageChangeListener());
        viewPager_slide.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        System.out.println("cancel");
                        break;

                    case MotionEvent.ACTION_UP:
                        // se deja de tocar el slider

                        if (lista_slideItems != null && lista_slideItems.size() != 0) {
                            detenerSlider = false;
                            runnable(lista_slideItems.size());
                            handler.postDelayed(animacionViewPager,
                                    ANIMACION_VIEWPAGER_DELAY_USER_VIEW);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // se toca el slider
                        if (handler != null && detenerSlider == false) {
                            detenerSlider = true;
                            handler.removeCallbacks(animacionViewPager);
                        }
                        break;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        if (lista_slideItems == null) {
            task = new ImageSlideTask(activity,this);
            task.execute();
        } else {
            viewPager_slide.setAdapter(new ImageSlideAdapter(activity, lista_slideItems,
                    MainFragment.this));

            txv_nombre_imagen.setText(""
                    + ((SlideItem) lista_slideItems.get(viewPager_slide.getCurrentItem()))
                    .getNombre());
            runnable(lista_slideItems.size());

            handler.postDelayed(animacionViewPager, ANIMACION_VIEWPAGER_DELAY);
        }
        super.onResume();
    }


    @Override
    public void onPause() {
        if (task != null)
            task.cancel(true);
        if (handler != null) {

            handler.removeCallbacks(animacionViewPager);
        }
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void fin_conexion_slide(Boolean estado, String mensaje, List<SlideItem> slideItems) {

        if(estado){

            if (slideItems != null && slideItems.size() != 0) {
                lista_slideItems=slideItems;

                viewPager_slide.setAdapter(new ImageSlideAdapter(
                        activity, lista_slideItems, MainFragment.this));
                txv_nombre_imagen.setText(""
                        + ((SlideItem) lista_slideItems.get(viewPager_slide
                        .getCurrentItem())).getNombre());
                runnable(lista_slideItems.size());
                handler.postDelayed(animacionViewPager,
                        ANIMACION_VIEWPAGER_DELAY);
            } else {
                txv_nombre_imagen.setText("No hay slide items");
            }
        }else{
            Toast.makeText(context, mensaje,Toast.LENGTH_LONG).show();
        }
    }

    private void findViewById(View view) {
        viewPager_slide = (ViewPager) view.findViewById(R.id.view_pager);
        txv_nombre_imagen = (TextView) view.findViewById(R.id.txv_nombre_img);
    }

    public void runnable(final int size) {
        handler = new Handler();
        animacionViewPager = new Runnable() {
            public void run() {
                if (!detenerSlider) {
                    if (viewPager_slide.getCurrentItem() == size - 1) {
                        viewPager_slide.setCurrentItem(0);
                    } else {
                        viewPager_slide.setCurrentItem(
                                viewPager_slide.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animacionViewPager, ANIMACION_VIEWPAGER_DELAY);
                }
            }
        };
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (lista_slideItems != null) {
                    txv_nombre_imagen.setText(""
                            + ((SlideItem) lista_slideItems.get(viewPager_slide
                            .getCurrentItem())).getNombre());
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }
}
