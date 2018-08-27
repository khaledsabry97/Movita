package com.example.khaledsabry.entertainment.Controllers;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.net.PortUnreachableException;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class Constants {

    private int width;
    private int height;
    private boolean isTablet = false;
    public static  final String YoutubeApiKey = "AIzaSyDyMlMX1NEZJUggZdxiFErwuocJSYm7Wp4";
    public static final String TmdbApiKey = "3628f9974c19710b3a434cf958458799";
    public static final String youtubeConnectionBaseUrl = "https://www.googleapis.com/youtube/v3/search?key="+YoutubeApiKey+"&part=snippet";
    public static final String tmdbBaseUrl = "https://api.themoviedb.org/3/";

    public String getTmdbBaseUrl() {
        return tmdbBaseUrl;
    }

    public boolean isTablet() {
        return isTablet;
    }

    public void setTablet(boolean tablet) {
        isTablet = tablet;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static final Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    private Constants() {
    }



    public void setWidthAndHeight(WindowManager windowManager)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

}