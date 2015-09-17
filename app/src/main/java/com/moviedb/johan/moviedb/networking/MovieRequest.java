package com.moviedb.johan.moviedb.networking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.utils.NetworkingUtils;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Johan on 16/09/15.
 */
public class MovieRequest {

    private static final String EXTENSION = "/movie/";

    private static final String API_KEY_EXTENSION = "api_key";

    public static com.squareup.okhttp.Request buildRequest(long id) throws IOException {
        String urlString = NetworkingUtils.baseUrl + EXTENSION + id ;

        HttpUrl url = HttpUrl.parse(urlString).newBuilder()
                .scheme("https")
                .addQueryParameter(API_KEY_EXTENSION, NetworkingUtils.apiKey)
                .build();

        return new com.squareup.okhttp.Request.Builder()
                .url(url)
                .build();
    }

    public static Movie getMovieInfo(long id) throws IOException{
        Movie movies = parseMovie(getMovieResponse(id).body().string());
        return movies;
    }

    private static Movie parseMovie(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        ObjectReader reader = mapper.reader(Movie.class);
        return reader.readValue(root.toString());
    }

    private static Response getMovieResponse(long id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new UserAgentInterceptor());
        return client.newCall(buildRequest(id)).execute();
    }
}
