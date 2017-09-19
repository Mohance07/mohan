package com.example.bewo.moviesearch.model.movieinfo;

import com.example.bewo.moviesearch.Utils;
import com.example.bewo.moviesearch.pojos.MovieDetails;
import com.example.bewo.moviesearch.pojos.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by BEWO on 18-09-2017.
 */

public interface MovieInfoResponseInterface {
    @GET(Utils.NOW_MOVIE_PLAYING_LIST)
    Call<MovieList> getMovieInfo(@Query("api_key") String apiKey,
                                 @Query("language") String language,
                                 @Query("query") String strMovieName);
}
