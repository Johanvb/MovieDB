package com.moviedb.johan.moviedb.viewmodels;

import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.views.MovieView;

/**
 * Created by Johan on 16/09/15.
 */
public class MovieViewModel {

    Movie movie;

    public MovieViewModel(Movie movie) {
        this.movie = movie;
    }

    public void bind(final MovieView movieView) {

        movieView.setMovieImage("http://image.tmdb.org/t/p/w500", movie.getPosterPath());
        movieView.setMovieTitle(movie.getTitle());
        movieView.setMovieDescription(movie.getOverview());

        movieView.setRatingAverage(movie.getVoteAverage());
        movieView.setRatingCount(movie.getVoteCount());
        movieView.setReleaseDate(movie.getReleaseDate());
        movieView.setLanguage(movie.getOriginalLanguage());


    }

}
