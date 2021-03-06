package com.example.khaledsabry.entertainment.Adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.YoutubeController;
import com.example.khaledsabry.entertainment.Fragments.WebFragment;
import com.example.khaledsabry.entertainment.Fragments.YoutubeFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnYoutubeSuccess;
import com.example.khaledsabry.entertainment.Items.Youtube;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 25-Jul-18.
 */

public class YoutubeOptionAdapter extends RecyclerView.Adapter<YoutubeOptionAdapter.YoutubeOptionViewHolder> {

    ArrayList<YoutubeController.Type> types;
    ArrayList<String> titles;//position to check which position is selected
    private int selectedPosition = 0;
    String query;
    String year;
    YoutubeFragment youtubeFragment;
    WebFragment webFragment;

    /**
     * constructor get
     *
     * @param types  types that youtube controller use it to identify the search query
     * @param titles used later to write the buttons you choose from it
     * @param query  the name of the movie,tv or the artist but without using the date
     * @param year   the date of the product we search for
     */
    public YoutubeOptionAdapter(YoutubeFragment youtubeFragment,WebFragment webFragment, ArrayList<YoutubeController.Type> types, ArrayList<String> titles, String query, String year) {
        this.types = types;
        this.titles = titles;
        this.query = query;
        this.year = year;
        this.youtubeFragment = youtubeFragment;
        this.webFragment = webFragment;
        select();
    }

    /**
     * search for youtube videos according to selected index in types
     */
    public void select() {
        YoutubeController youtubeController = new YoutubeController();
        youtubeController.search(query, year, types.get(selectedPosition), new OnYoutubeSuccess() {
            @Override
            public void onSuccess(ArrayList<Youtube> youtubes) {
                if (youtubeFragment != null)
                    youtubeFragment.loadVideos(youtubes);
            }
        });
    }

    @Override
    public YoutubeOptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_options, parent, false);
        return new YoutubeOptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeOptionViewHolder holder, final int position) {

        //if selected position is equal to that mean view is selected so change the cardview color
        if (selectedPosition == position) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.getActivity().getApplicationContext(), R.color.colorPrimary));
        } else {
            //if selected position is not equal to that mean view is not selected so change the cardview color to white back again
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.getActivity().getApplicationContext(), android.R.color.white));
        }

        holder.updateUi(types.get(position), titles.get(position), position);

    }

    @Override
    public int getItemCount() {
        if (types == null)
            return 0;
        return types.size();
    }

    /**
     * method the change the selected position when item clicked
     *
     * @param selectedPosition
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }

    class YoutubeOptionViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CardView cardView;

        public YoutubeOptionViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.cardview);

        }

        /**
         * update the options recycler view
         *
         * @param type     defiend in youtube controller to adjust search query in youtube api
         * @param name     the title of the button
         * @param position the position of the button
         */
        private void updateUi(final YoutubeController.Type type, String name, final int position) {
            title.setText(name);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type == YoutubeController.Type.soundtrack)
                        if(webFragment !=null)
                            webFragment.setType(WebFragment.Type.youtube_music);

                    if(webFragment != null)
                        webFragment.setType(WebFragment.Type.youtube);
                    setSelectedPosition(position);
                    select();

                }
            });
        }

    }
}
