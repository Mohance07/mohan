package com.example.bewo.moviesearch.view.mainscreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bewo.moviesearch.R;
import com.example.bewo.moviesearch.Utils;
import com.example.bewo.moviesearch.adapter.DataAdapter;
import com.example.bewo.moviesearch.pojos.MovieDetails;
import com.example.bewo.moviesearch.pojos.MovieList;
import com.example.bewo.moviesearch.presenter.movieinfo.MovieInfoContract;
import com.example.bewo.moviesearch.presenter.movieinfo.MovieInfoPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieInfoContract.ViewContract{
    private static final String TAG = "MovieSearch";
    MovieInfoPresenter movieInfoPresenter = null;
    public ArrayList<MovieDetails> data;
    ProgressDialog progressDialog;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieInfoPresenter = new MovieInfoPresenter(this);
        data = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        movieInfoPresenter.getMovies(Utils.API_KEY,"en-US",null);
    }

    private void initViews(){
        mRecyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void alertUser(String title, String text) {

    }

    @Override
    public void displayLoading(String title, String msg) {
        if(progressDialog==null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(msg);
        }
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void renderView(MovieList movies) {
        try{
            data=movies.getMovies();
            //data.add(null);
            mAdapter = new DataAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }catch(Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (mAdapter != null) mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
