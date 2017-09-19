package com.example.bewo.moviesearch.view.BaseView;

/**
 * Created by BEWO on 18-09-2017.
 */

public interface BaseContract {
    interface BaseView {
        public void alertUser(String title, String text);
        public void displayLoading(String title, String msg);
        public void dismissLoading();
    }
}