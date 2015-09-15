package com.moviedb.johan.moviedb.viewmodels;

import android.os.AsyncTask;
import android.util.Log;

import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.networking.SearchRequest;
import com.moviedb.johan.moviedb.views.SearchView;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchViewModel implements SearchView.TextChangedListener {

    SearchView searchView;


    public void bind(SearchView searchView){

        this.searchView = searchView;
        searchView.setup(this);

        new AsyncTask<Void, Void, Movie[]>() {
            @Override
            protected Movie[] doInBackground(Void... params) {
                try {

                    Movie[] movies = SearchRequest.getMovies("Fight");


                    return movies;
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                    return null;
                }


            }

            @Override
            protected void onPostExecute(Movie[] movies) {
                super.onPostExecute(movies);
            }

        }.execute();

    }


    @Override
    public void onTextChanged(String text) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

//                    User receivedUser = UserRequest.getUser(Server.INSTANCE.getEndPoint(ProfileActivity.this), SharedPrefUtils.getToken(), user.getId());




                } catch (Exception e) {
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

            }
        }.execute();



    }
}
