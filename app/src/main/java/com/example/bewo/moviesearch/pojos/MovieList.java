package com.example.bewo.moviesearch.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BEWO on 18-09-2017.
 */

public class MovieList {
    @SerializedName("results")
    private ArrayList<MovieDetails> movies;

    public ArrayList<MovieDetails> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieDetails> movies) {
        this.movies = movies;
    }
}
