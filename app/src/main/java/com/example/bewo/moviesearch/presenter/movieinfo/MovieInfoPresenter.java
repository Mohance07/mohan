package com.example.bewo.moviesearch.presenter.movieinfo;

import android.util.Log;

import com.example.bewo.moviesearch.model.movieinfo.MovieInfoLoaderListener;
import com.example.bewo.moviesearch.model.movieinfo.MovieInfoResponseManager;
import com.example.bewo.moviesearch.pojos.MovieDetails;
import com.example.bewo.moviesearch.pojos.MovieList;

import java.util.ArrayList;

/**
 * Created by BEWO on 18-09-2017.
 */

public class MovieInfoPresenter implements MovieInfoLoaderListener, MovieInfoContract.PresenterContract {
    MovieInfoResponseManager movieInfoResponseManager;
    MovieInfoContract.ViewContract viewContract;

    public MovieInfoPresenter(MovieInfoContract.ViewContract viewContract){
        movieInfoResponseManager=new MovieInfoResponseManager(this);
        this.viewContract=viewContract;
    }
    @Override
    public void onSuccess(MovieList movieList) {
        viewContract.dismissLoading();
        try {
            ArrayList<MovieDetails> movies = (ArrayList<MovieDetails>) movieList.getMovies();
            movieList.setMovies(movies);

            viewContract.dismissLoading();
            viewContract.renderView(movieList);
        }catch(Exception e){
            Log.d("ShowTimezz",e.getLocalizedMessage());
        }
    }

    @Override
    public void onFailure(String error) {
        viewContract.dismissLoading();
    }

    @Override
    public void getMovies(String apiKey, String langauge, String strMovieName) {
        viewContract.displayLoading("Now Fetching Data","Loading Data Please wait");
        movieInfoResponseManager.getMovieInfo(apiKey,langauge,strMovieName);
    }
}
