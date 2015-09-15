package com.moviedb.johan.moviedb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

import java.util.Calendar;

/**
 * Created by Johan on 15/09/15.
 */
@Parcel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {

    @JsonProperty("id")
    long id;


    @JsonProperty("original_language")
    String originalLanguage;

    @JsonProperty("overview")
    String overview;


    @JsonProperty("title")
    String title;

    @JsonProperty("release_date")
    Calendar releaseDate;

    @JsonProperty("vote_average")
    float voteAverage;

    @JsonProperty("vote_count")
    int voteCount;

    @JsonProperty("original_title")
    String originalTitle;

    @JsonProperty("adult")
    boolean isAdult;

    @JsonProperty("poster_path")
    String posterPath;

    @JsonProperty("backdrop_path")
    String backdropPath;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

}
