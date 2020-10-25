package com.demo.mymovie.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mymovie.R;
import com.demo.mymovie.base.BaseFragment;
import com.demo.mymovie.data.model.Movie;
import com.demo.mymovie.ui.main.MovieListAdapter;
import com.demo.mymovie.utils.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class ListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;

    private ArrayList<Movie> movies=new ArrayList<>();

    private MovieListAdapter movieAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.movie_list;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(ListViewModel.class);
        getPopularMovies();

    }

    private void getPopularMovies() {
        viewModel.getMovies().observe(this, movies -> {
            if(movies != null) listView.setVisibility(View.VISIBLE);
            movieAdapter = new MovieListAdapter(getActivity(), (ArrayList<Movie>) movies);
            listView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            listView.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });

    }
}
