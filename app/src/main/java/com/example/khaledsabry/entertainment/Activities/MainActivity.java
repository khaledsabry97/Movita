package com.example.khaledsabry.entertainment.Activities;

import android.icu.util.Calendar;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.Downloader;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Fragments.BoxOfficeFragment;
import com.example.khaledsabry.entertainment.Fragments.CategoryListFragment;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.MainMenuFragment;
import com.example.khaledsabry.entertainment.Fragments.NewsFragment;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchFragment;
import com.example.khaledsabry.entertainment.Fragments.UserProfile.ProfileFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Controllers.Settings;
import com.example.khaledsabry.entertainment.Fragments.SignInFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.nio.file.Files.copy;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //to get the main activity in the app
    private static MainActivity mainActivity;

    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get width and height for the mobile and tablet
        Settings.getInstance().setWidthAndHeight(getWindowManager());
        //set the context for the volley library
        ApiConnections.getInstance().setContext(getApplicationContext());


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Functions.stopConnectionsAndStartImageGlide();
                switch (id) {
                    case R.id.home:
                        MainActivity.loadFragmentWithReturn(R.id.mainContainer, MainMenuFragment.newInstance());
                        break;
                    case R.id.searchid:
                        loadFragmentNoReturn(R.id.mainContainer, SearchFragment.newInstance());
                        break;

                    case R.id.boxofficeid:
                        loadFragmentNoReturn(R.id.mainContainer, BoxOfficeFragment.newInstance());
                        break;

                    case R.id.category:
                        MainActivity.loadFragmentWithReturn(R.id.mainContainer, CategoryListFragment.newInstance());
                        break;
                    case R.id.news:
                        loadFragmentNoReturn(R.id.mainContainer, NewsFragment.newInstance());
                        break;
                    default:
                        break;
                }


                navigationView.setCheckedItem(id);
                drawerLayout.closeDrawer(GravityCompat.START, true);
                return true;

            }
        });


        //   loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(299536,true));
// hide the navigation bar and the status bar
        periodicHideNavigation();
//loadFragmentNoReturn(R.id.mainContainer, SearchFragment.newInstance());
        //    loadFragmentWithReturn(R.id.mainContainer, MainMenuFragment.newInstance());

  //      Downloader.getInstance().downloadYoutube("https://www.youtube.com/watch?v=h7NLOLUOxh4", "Slender Man - Movie Review");

        loadFragmentNoReturn(R.id.mainContainer, SignInFragment.newInstance());

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideSystemUI();

    }

    public void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


    }


    public static void loadFragmentWithReturn(int idContainer, android.support.v4.app.Fragment fragment) {

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).addToBackStack(null).commit();
    }

    public static void loadFragmentNoReturn(int idContainer, android.support.v4.app.Fragment fragment) {

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).commit();
    }

    private void periodicHideNavigation() {
        final Handler handler = new Handler();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hideSystemUI();

                    }
                });

            }
        }, 1000, 2000);
    }

}
