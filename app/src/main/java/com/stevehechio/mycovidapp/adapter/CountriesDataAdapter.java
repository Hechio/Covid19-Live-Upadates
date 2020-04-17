package com.stevehechio.mycovidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stevehechio.mycovidapp.R;
import com.stevehechio.mycovidapp.holders.CountriesDataHolder;
import com.stevehechio.mycovidapp.models.CountryDataDTO;

import java.util.ArrayList;

/**
 * Created by Hechio on 4/17/2020.\
 */
public class CountriesDataAdapter extends RecyclerView.Adapter<CountriesDataHolder> {
    private ArrayList<CountryDataDTO> arrayList = new ArrayList<>();
    private Context context;

    public CountriesDataAdapter(ArrayList<CountryDataDTO> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountriesDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries_data, parent, false);
        return new CountriesDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesDataHolder holder, int position) {
        CountryDataDTO dataDTO=arrayList.get(position);
        holder.setTvCases(String.valueOf(dataDTO.getCases()));
        holder.setTvName(dataDTO.getCountry());
        holder.setTvRecovered(String.valueOf(dataDTO.getRecovered()));
        holder.setTvDeaths(String.valueOf(dataDTO.getDeaths()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
