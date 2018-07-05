package com.example.khaledsabry.entertainment.Fragments.MovieView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.CrewRecyclerAdapter;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 29-Jun-18.
 */

public class CrewFragment extends Fragment {
    RecyclerView recyclerView;
    private static Movie movie;

    public static CrewFragment newInstance(Movie movie) {
        CrewFragment fragment = new CrewFragment();
        CrewFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cast, container, false);

        recyclerView = v.findViewById(R.id.contentPanel);
        recyclerView.setHasFixedSize(true);


        CrewRecyclerAdapter adapter = new CrewRecyclerAdapter(movie.getCrews());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        return v;
    }
}