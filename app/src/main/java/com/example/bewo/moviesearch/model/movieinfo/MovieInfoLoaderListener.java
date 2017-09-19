package com.example.bewo.moviesearch.model.movieinfo;

import com.example.bewo.moviesearch.pojos.MovieDetails;
import com.example.bewo.moviesearch.pojos.MovieList;

/**
 * Created by BEWO on 18-09-2017.
 */

public interface MovieInfoLoaderListener {
    void onSuccess(MovieList movieList);
    void onFailure(String error);
}
