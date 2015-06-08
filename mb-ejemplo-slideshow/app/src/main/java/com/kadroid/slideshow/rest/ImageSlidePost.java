package com.kadroid.slideshow.rest;

import com.kadroid.slideshow.model.SlideItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalu on 07/06/2015.
 */
public interface ImageSlidePost {
    public void fin_conexion_slide(Boolean estado, String mensaje, List<SlideItem> slideItems);
}
