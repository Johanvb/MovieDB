package com.moviedb.johan.moviedb.networking;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Johan on 15/09/15.
 */
public class UserAgentInterceptor implements Interceptor {

    public UserAgentInterceptor() {}

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                    .build();
        return chain.proceed(requestWithUserAgent);
    }
}