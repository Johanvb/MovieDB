package com.moviedb.johan.moviedb.viewmodels;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.networking.SearchRequest;
import com.moviedb.johan.moviedb.views.MovieItemView;
import com.moviedb.johan.moviedb.views.SearchView;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchViewModel implements SearchView.TextChangedListener {

    SearchView searchView;

    Movie[] movies;
    RecyclerView.Adapter mAdapter;

    public void bind(SearchView searchView){

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
            public void onBindViewHolder(MovieItemView viewHolder, int i) {
                viewHolder.setTitleText(movies[i].getTitle());
                viewHolder.setMovieYear(movies[i].getYear());

                try {
                    viewHolder.setImageFromUrl("http://image.tmdb.org/t/p/w300" + movies[i].getPosterPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public int getItemCount() {
                return movies.length;
            }

        };

        searchView.setup(this,mAdapter);


    }


    @Override
    public void onTextChanged(final String text) {
        movies = new Movie[0];
        mAdapter.notifyDataSetChanged();

        if (text.length() == 0) return;

        new AsyncTask<Void, Void, Movie[]>() {
            @Override
            protected Movie[] doInBackground(Void... params) {
                try {

                    Movie[] movies = SearchRequest.getMovies(text);

                    return movies;
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Movie[] newMovies) {
                super.onPostExecute(newMovies);

                if(newMovies != null && newMovies.length != 0){
                    movies = newMovies;
                    mAdapter.notifyDataSetChanged();
                }
            }

        }.execute();


    }
}
