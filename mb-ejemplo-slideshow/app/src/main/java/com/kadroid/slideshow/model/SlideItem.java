package com.kadroid.slideshow.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kalu on 07/06/2015.
 */
public class SlideItem{

    private int id;
    private String nombre;
    private String url;

    public SlideItem() {
        super();
    }

    public SlideItem(int id, String nombre, String url){
        super();
        this.id=id;
        this.nombre =nombre;
        this.url =url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}