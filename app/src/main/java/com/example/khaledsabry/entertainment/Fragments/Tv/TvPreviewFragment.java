package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;


public class TvPreviewFragment extends Fragment {


    static Tv tv;

    ImageView poster;
    TextView title;
    TextView rate;
    TextView airDate;
    TextView overView;
    TextView download;

    public static TvPreviewFragment newInstance(Tv tv) {
        TvPreviewFragment fragment = new TvPreviewFragment();
        TvPreviewFragment.tv = tv;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_preview, container, false);
        title = view.findViewById(R.id.title);
        rate = view.findViewById(R.id.rateid);

        airDate = view.findViewById(R.id.airdateid);

        overView = view.findViewById(R.id.overviewid);
        poster = view.findViewById(R.id.posterrelativelayout);
        download = view.findViewById(R.id.downloadid);


        setObjects();

        if (tv != null)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, TvNavigationFragment.newInstance(tv.getId(), true)).addToBackStack(null).commit();
                }
            });


        return view;
    }

    private void setObjects() {
        if (tv != null) {
            title.setText(tv.getTitle());
            rate.setText(tv.getRateTmdb() + "/10");
            airDate.setText("First Air Date : " + tv.getFirstAirDate());
            overView.setText(tv.getOverView());
            ImageController.putImageMidQuality(tv.getBackDrop(), poster);
        }

    }




}
