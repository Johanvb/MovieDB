package com.moviedb.johan.moviedb.viewmodels;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.activities.MovieActivity;
import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.networking.SearchRequest;
import com.moviedb.johan.moviedb.views.MovieItemView;
import com.moviedb.johan.moviedb.views.SearchView;

import org.parceler.Parcels;

import java.io.IOException;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchViewModel implements SearchView.TextChangedListener {

    SearchView searchView;

    Movie[] movies;
    RecyclerView.Adapter mAdapter;
    String currentText;

    public void bind(final SearchView searchView) {

        this.searchView = searchView;
        movies = new Movie[0];

        mAdapter = new RecyclerView.Adapter<MovieItemView>() {
            @Override
            public MovieItemView onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowview_movie
                        , viewGroup, false);
                return new MovieItemView(view);
            }

            @Override
            public void onBindViewHolder(final MovieItemView viewHolder, final int i) {
                viewHolder.setTitleText(movies[i].getTitle());
                viewHolder.setMovieYear(movies[i].getYear());
                viewHolder.setMovieRating(movies[i].getVoteAverage());

                try {
                    viewHolder.setImageFromUrl("http://image.tmdb.org/t/p/w500" + movies[i].getPosterPath());
                } catch (IOException ignored) {}

                viewHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(searchView.getContext(), MovieActivity.class);
                        intent.putExtra(MovieActivity.EXTRA_MOVIE, Parcels.wrap(movies[i]));

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) searchView.getContext(),
                                    new Pair<>(viewHolder.getMovieImage(), "movie_image"),
                                    new Pair<>(viewHolder.getMovieTitle(), "movie_title"));

                            searchView.getContext().startActivity(intent, options.toBundle());
                        } else {
                            searchView.getContext().startActivity(intent);
                        }

                    }
                });
            }

            @Override
            public int getItemCount() {
                return movies.length;
            }

        };

        searchView.setup(this, mAdapter);
        searchView.setupRefresh(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchView.setRefreshing(true);
                onTextChanged(currentText);
            }
        });

    }


    @Override
    public void onTextChanged(final String text) {
        currentText = text;
        movies = new Movie[0];
        mAdapter.notifyDataSetChanged();

        searchView.setRefreshable(false);
        searchView.setLoading(text.length() != 0);


        if (text.length() == 0) {
            searchView.showSearchMessage();
            return;
        }
        searchView.setErrorMessage(false);


        new AsyncTask<Void, Void, Movie[]>() {
            @Override
            protected Movie[] doInBackground(Void... params) {
                try {

                    Movie[] movies = SearchRequest.getMovies(text);

                    return movies;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Movie[] newMovies) {
                super.onPostExecute(newMovies);
                searchView.setLoading(false);
                searchView.setRefreshable(text.length() != 0);

                if (newMovies != null && newMovies.length != 0) {
                    movies = newMovies;
                    mAdapter.notifyDataSetChanged();
                    searchView.setErrorMessage(false);
                } else {
                    searchView.setErrorMessage(true);
                }
                searchView.setRefreshing(false);

            }

        }.execute();

    }
}
