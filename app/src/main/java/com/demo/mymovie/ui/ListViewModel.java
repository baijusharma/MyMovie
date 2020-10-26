package com.demo.mymovie.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.mymovie.data.model.Movie;
import com.demo.mymovie.data.model.MovieResponse;
import com.demo.mymovie.data.rest.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final MovieRepository repoRepository;
    private CompositeDisposable disposable;
    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private ArrayList<Movie> arrmovie;

    LiveData<List<Movie>> getMovies() {
        return movies;
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    @Inject
    public ListViewModel(MovieRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchMovies();
    }

    /**
     * This function will fetch the movies and send it to the view
     */
    private void fetchMovies() {
        loading.setValue(true);
        arrmovie = new ArrayList<>();
        disposable.add(repoRepository.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<MovieResponse, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> apply(MovieResponse movieDBResponse) throws Exception {
                        return Observable.fromArray(movieDBResponse.getMovies().toArray(new Movie[0]));
                    }
                }).subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        arrmovie.add(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        repoLoadError.setValue(false);
                        loading.setValue(false);
                        movies.setValue(arrmovie);
                    }
                }));

    }

    /**
     * Here we dispose all the the subscription,to avoid memory leaks
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
