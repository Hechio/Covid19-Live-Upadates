package com.stevehechio.mycovidapp.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stevehechio.mycovidapp.R;

/**
 * Created by Hechio on 4/17/2020.\
 */
public class CountriesDataHolder extends RecyclerView.ViewHolder {
    private TextView tvCases,tvName,tvRecovered,tvDeaths;
    public CountriesDataHolder(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tv_item_country);
        tvCases=itemView.findViewById(R.id.tv_item_cases);
        tvRecovered=itemView.findViewById(R.id.tv_item_recovered);
        tvDeaths=itemView.findViewById(R.id.tv_item_deaths);
    }
    public void setTvCases(String cases){
        tvCases.setText(cases);
    }
    public void setTvName(String name){
        tvName.setText(name);
    }
    public void setTvRecovered(String recovered){
        tvRecovered.setText(recovered);
    }
    public void setTvDeaths(String deaths){
        tvDeaths.setText(deaths);
    }
}
