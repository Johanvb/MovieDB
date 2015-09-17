package com.moviedb.johan.moviedb.networking;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.moviedb.johan.moviedb.entities.Movie;
import com.moviedb.johan.moviedb.utils.NetworkingUtils;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchRequest {

    private static final String EXTENSION = "/search/movie";

    private static final String API_KEY_EXTENSION = "api_key";
    private static final String QUERY_EXTENSION = "query";

    public static com.squareup.okhttp.Request buildRequest(String query) throws IOException {


        String urlString = NetworkingUtils.baseUrl + EXTENSION ;

          HttpUrl url = HttpUrl.parse(urlString).newBuilder()
                .scheme("https")
                .addQueryParameter(API_KEY_EXTENSION, NetworkingUtils.apiKey)
                .addQueryParameter(QUERY_EXTENSION, query).build();

        return new com.squareup.okhttp.Request.Builder()
                .url(url)
                .build();
    }

    public static Movie[] getMovies(String query) throws IOException{
        Movie[] movies = parseMovies(getMoviesResponse(query).body().string());
        return movies;
    }

    private static Movie[] parseMovies(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        ObjectReader reader = mapper.reader(Movie[].class);
        return reader.readValue(root.get("results").toString());
    }

    private static Response getMoviesResponse(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new UserAgentInterceptor());
        return client.newCall(buildRequest(query)).execute();
    }

}
