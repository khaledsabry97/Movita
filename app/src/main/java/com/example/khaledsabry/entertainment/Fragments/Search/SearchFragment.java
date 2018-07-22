package com.example.khaledsabry.entertainment.Fragments.Search;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnSearchSuccess;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    TmdbController tmdbController;
    SearchView searchView;
    int value = 1;
    int currentvalue = 2;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = v.findViewById(R.id.searchid);

        tmdbController = new TmdbController();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                ApiConnections.getInstance().stopConnection();
                tmdbController.search(query, new OnSearchSuccess() {
                    @Override
                    public void onSuccess(ArrayList<SearchItem> searchItems) {
                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.resultsid, SearchResult.newInstance(searchItems)).commit();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                tmdbController.search(query, new OnSearchSuccess() {
                    @Override
                    public void onSuccess(ArrayList<SearchItem> searchItems) {
                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.resultsid, SearchResult.newInstance(searchItems)).commit();
                    }
                });


                return true;
            }
        });


        return v;
    }

}
