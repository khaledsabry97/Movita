package com.example.khaledsabry.entertainment.Fragments;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.khaledsabry.entertainment.Adapter.TorrentAdapter;
import com.example.khaledsabry.entertainment.Controllers.TorrentController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class TorrentRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    static String searchName;
    // Spinner provider;
    // Spinner quality;
    // Spinner resolution;

    MaterialSpinner provider;
    MaterialSpinner quality;
    MaterialSpinner resolution;
    MaterialSpinner codec;
    SearchView customSearch;
    TorrentController torrentController;
    String search = "";
    public static TorrentRecyclerFragment newInstance(String searchName) {
        TorrentRecyclerFragment fragment = new TorrentRecyclerFragment();
        TorrentRecyclerFragment.searchName = searchName;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torrent_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);
        resolution = view.findViewById(R.id.resolutionspinnerid);
        provider = view.findViewById(R.id.providerspinnerid);
        quality = view.findViewById(R.id.qualityspinnerid);
        customSearch = view.findViewById(R.id.customtextid);
        codec = view.findViewById(R.id.codecspinnerid);
        torrentController = new TorrentController();

        setResolution();
        setQuality();
        setProvider();
        setCodec();
        setCustomSearch();

        search();



        return view;
    }

    private void search() {
        String mprovider = "";
        String mquality = "";
        String mresolution = "";
        String mcodec = "";

        if (!resolution.getItems().get(resolution.getSelectedIndex()).toString().equals("All"))
            mresolution = " " + resolution.getItems().get(resolution.getSelectedIndex()).toString();
        if (!quality.getItems().get(quality.getSelectedIndex()).toString().equals("All"))
            mquality = " " + quality.getItems().get(quality.getSelectedIndex()).toString();
        if (!codec.getItems().get(codec.getSelectedIndex()).toString().equals("All"))
            mcodec = " " + codec.getItems().get(codec.getSelectedIndex()).toString();
        if (!provider.getItems().get(provider.getSelectedIndex()).toString().equals("All"))
            mprovider = " " + provider.getItems().get(provider.getSelectedIndex()).toString();

        if (!search.equals(searchName + mresolution + mquality + mcodec + mprovider)) {
            search = searchName + mresolution + mquality + mcodec + mprovider;
            torrentController.downloadSkyTorrent(search, new OnTorrentSearchSuccess() {
                @Override
                public void onSuccess(ArrayList<Torrent> torrents) {

                    setObjects(torrents);

                }
            });
        }

    }

    private void setCodec() {
        ArrayList<String> adapter = new ArrayList<>();

        adapter.add("All");
        adapter.add("x264");
        adapter.add("x265");
        adapter.add("10bit x265");


        codec.setItems(adapter);
        codec.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                search();
            }
        });
    }

    private void setProvider() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("PSA");
        adapter.add("YTS");
        adapter.add("MkvCage");
        adapter.add("SPARKS");
        adapter.add("RARBG");
        adapter.add("Ganool");
        adapter.add("YIFY");


        provider.setItems(adapter);
        provider.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                search();
            }
        });
    }

    private void setQuality() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("CAM");
        adapter.add("TS");
        adapter.add("HDTV");
        adapter.add("DVDSCR");
        adapter.add("HDRip");

        adapter.add("WEBRip");
        adapter.add("WEBDL");
        adapter.add("BRRip");
        adapter.add("BluRay");

        quality.setItems(adapter);
        quality.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                search();
            }
        });

    }

    private void setResolution() {

        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("480p");
        adapter.add("720p");
        adapter.add("1080p");
        adapter.add("2160p");
        resolution.setItems(adapter);
        resolution.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                search();
            }
        });
    }

    private void setObjects(ArrayList<Torrent> torrents) {
        TorrentAdapter torrentAdapter = new TorrentAdapter(torrents);
        recyclerView.setAdapter(torrentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);


    }


    private void setCustomSearch()
    {
        customSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                torrentController.downloadSkyTorrent(query, new OnTorrentSearchSuccess() {
                    @Override
                    public void onSuccess(ArrayList<Torrent> torrents) {
                        setObjects(torrents);
                    }
                });


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }




    });

}
}
