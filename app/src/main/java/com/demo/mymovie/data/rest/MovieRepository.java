package com.demo.mymovie.data.rest;

import com.demo.mymovie.data.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.demo.mymovie.utils.Constant.api_key;

public class MovieRepository {

    private final MovieService movieService;

    @Inject
    public MovieRepository(MovieService movieService) {
        this.movieService = movieService;
    }

    public Observable<MovieResponse> getPopularMovies() {
        return movieService.getPopularMoviesWithRx(api_key);
    }
}
