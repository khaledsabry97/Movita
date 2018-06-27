package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import com.example.khaledsabry.entertainment.Adapter.RecyclerAdapter;


public class CastFragment extends Fragment {
    RecyclerView recyclerView;
    private static Movie movie;


    public static CastFragment newInstance(Movie movie) {
        CastFragment fragment = new CastFragment();
CastFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cast, container, false);
        recyclerView = v.findViewById(R.id.contentPanel);
        recyclerView.setHasFixedSize(true);

        RecyclerAdapter adapter;
        adapter = new RecyclerAdapter(movie.getCharacters());

        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4,GridLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        return v;
    }
}