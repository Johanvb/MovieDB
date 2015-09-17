package com.moviedb.johan.moviedb.viewmodels;

import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.views.MovieView;

/**
 * Created by Johan on 16/09/15.
 */
public class MovieViewModel {

    Movie movie;
    MovieView movieView;

    public MovieViewModel(Movie movie) {
        this.movie = movie;
    }

    public void bind(MovieView  movieView) {
        this.movieView = movieView;
        movieView.setMovieImage("http://image.tmdb.org/t/p/w500", movie.getPosterPath());
        movieView.setMovieTitle(movie.getTitle());
        movieView.setMovieDescription(movie.getOverview());

        movieView.setRatingAverage(movie.getVoteAverage());
        movieView.setRatingCount(movie.getVoteCount());
        movieView.setReleaseDate(movie.getReleaseDate());

    }

    public void setDetailedMovieInfo(Movie newMovie){

        movie = newMovie;
        movieView.setTagLine(movie.getTagLine());
        movieView.setBudget(movie.getBudget());
        movieView.setGenres(movie.getGenres());
        movieView.setLanguages(movie.getLanguages());
        movieView.setStatus(movie.getStatus());
        movieView.setRuntime(movie.getRuntime());
        movieView.setRevenue(movie.getRevenue());

    }

}
