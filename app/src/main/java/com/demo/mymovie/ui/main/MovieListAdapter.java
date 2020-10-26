package com.demo.mymovie.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.mymovie.R;
import com.demo.mymovie.data.model.Movie;
import com.demo.mymovie.utils.Constant;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.movieTitle.setText(movies.get(position).getTitle());

        String posterPath = Constant.IMG_LOAD_URL + movies.get(position).getPosterPath();

        Glide.with(context)
                .load(posterPath)
                .placeholder(R.drawable.loading)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView movieTitle;
        public ImageView movieImage;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.tvTitle);
            movieImage = itemView.findViewById(R.id.ivMovie);

        }
    }
}
