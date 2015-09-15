package com.moviedb.johan.moviedb.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Johan on 15/09/15.
 */
public class MovieItemView extends RecyclerView.ViewHolder{

    TextView movieTitle;


    public MovieItemView(View itemView) {
        super(itemView);
        ButterKnife.inject(itemView);
        movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
    }


    public void setTitleText(String title) {
        movieTitle.setText(title);
    }
}
