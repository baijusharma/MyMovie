package com.demo.mymovie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

}
