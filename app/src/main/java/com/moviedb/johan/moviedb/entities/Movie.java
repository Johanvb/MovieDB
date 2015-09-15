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



}
