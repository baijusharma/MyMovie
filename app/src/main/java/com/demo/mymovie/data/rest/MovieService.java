package com.demo.mymovie.data.rest;

import com.demo.mymovie.data.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMoviesWithRx(@Query("api_key") String apiKey);
}
