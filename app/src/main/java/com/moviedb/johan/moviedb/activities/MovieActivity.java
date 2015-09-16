package com.moviedb.johan.moviedb.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.viewmodels.MovieViewModel;
import com.moviedb.johan.moviedb.views.MovieView;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Johan on 15/09/15.
 */
public class MovieActivity extends AppCompatActivity {

    @InjectView(R.id.movie_view)
    MovieView movieView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    MovieViewModel movieViewModel;


    public static String EXTRA_MOVIE = "extra_movie";
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.inject(this);
        movie = Parcels.unwrap(this.getIntent().getParcelableExtra(EXTRA_MOVIE));

        movieViewModel = new MovieViewModel(movie);
        movieViewModel.bind(movieView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setElevation(50);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_behind, R.anim.activity_close_front);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
