package com.moviedb.johan.moviedb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.entities.Movie;

import org.parceler.Parcels;

import butterknife.ButterKnife;

/**
 * Created by Johan on 15/09/15.
 */
public class MovieActivity extends AppCompatActivity {

    public static String EXTRA_MOVIE = "extra_movie";
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.inject(this);

        movie = Parcels.unwrap(this.getIntent().getParcelableExtra(EXTRA_MOVIE));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(movie.getTitle());


    }

    @Override
    public void onBackPressed(){

        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_behind, R.anim.activity_close_front);


    }


}
