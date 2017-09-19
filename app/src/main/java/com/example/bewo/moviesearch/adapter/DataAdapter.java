package com.example.bewo.moviesearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.bewo.moviesearch.R;
import com.example.bewo.moviesearch.pojos.MovieDetails;

import java.util.ArrayList;

/**
 * Created by BEWO on 18-09-2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ArrayList<MovieDetails> mArrayList;
    private ArrayList<MovieDetails> mFilteredList;

    public DataAdapter(ArrayList<MovieDetails> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(mFilteredList.get(i).getTitle());
        viewHolder.tv_release_date.setText(mFilteredList.get(i).getRelease_date());
        viewHolder.tv_id.setText(mFilteredList.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<MovieDetails> filteredList = new ArrayList<>();

                    for (MovieDetails movieDetails : mArrayList) {

                        if (movieDetails.getTitle().toLowerCase().contains(charString) ) {

                            filteredList.add(movieDetails);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<MovieDetails>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_release_date,tv_id;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_release_date = (TextView)view.findViewById(R.id.tv_release_date);
            tv_id = (TextView)view.findViewById(R.id.tv_id);

        }
    }

}