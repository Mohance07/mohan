package com.example.bewo.moviesearch.presenter.movieinfo;

import com.example.bewo.moviesearch.pojos.MovieDetails;
import com.example.bewo.moviesearch.pojos.MovieList;
import com.example.bewo.moviesearch.view.BaseView.BaseContract;

/**
 * Created by BEWO on 18-09-2017.
 */

public interface MovieInfoContract extends BaseContract.BaseView {
    interface PresenterContract {
        void getMovies(String apiKey, String langauge, String strMovieName);
    }

    interface ViewContract extends BaseContract.BaseView {
        void renderView(MovieList movies);
    }
}