package com.example.bewo.moviesearch.model.movieinfo;

import com.example.bewo.moviesearch.pojos.MovieDetails;
import com.example.bewo.moviesearch.pojos.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BEWO on 18-09-2017.
 */

public class MovieInfoResponseManager implements Callback<MovieList> {
    MovieInfoResponseInterface movieInfoResponseInterface;
    MovieInfoLoaderListener movieInfoLoaderListener;

    public MovieInfoResponseManager(MovieInfoLoaderListener movieInfoLoaderListener){
        this.movieInfoLoaderListener=movieInfoLoaderListener;
        movieInfoResponseInterface= ApiClient.getInstance().create(MovieInfoResponseInterface.class);
    }

    public void getMovieInfo(String apiKey,String language,String strMovieName){
        Call<MovieList> call=movieInfoResponseInterface.getMovieInfo(apiKey,language,strMovieName);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
        movieInfoLoaderListener.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<MovieList> call, Throwable t) {
        movieInfoLoaderListener.onFailure(t.getLocalizedMessage());
    }
}

