package com.stevehechio.mycovidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.stevehechio.mycovidapp.R;
import com.stevehechio.mycovidapp.adapter.CountriesDataAdapter;
import com.stevehechio.mycovidapp.models.CountryDataDTO;
import com.stevehechio.mycovidapp.models.CovidDTO;
import com.stevehechio.mycovidapp.utils.JSONPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private  JSONPlaceHolderApi jsonPlaceHolderApi;
    private SearchView mSearchView;
    private TextView tvTotalCases,tvTodayCases,tvDeaths,tvTodayDeaths,tvRecovered,tvActive,tvCritical,tvTests,tvName;
    private RecyclerView recyclerView;
    private ArrayList<CountryDataDTO> dtoArrayList =new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private CountriesDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        gridLayoutManager=new GridLayoutManager(this, 1);
        collectIDs();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);
       getGlobalData();
       getCountriesData();
       setUpSearchView();

    }

    private void collectIDs() {
        mSearchView=findViewById(R.id.search_view);
        tvTotalCases=findViewById(R.id.tv_total_c_cases);
        tvTodayCases=findViewById(R.id.tv_c_today_cases);
        tvDeaths=findViewById(R.id.txt_c_total_deaths);
        tvTodayDeaths=findViewById(R.id.tv_today_c_deaths);
        tvRecovered=findViewById(R.id.tv_c_total_recovered);
        tvActive=findViewById(R.id.tv_c_total_active);
        tvCritical=findViewById(R.id.tv_c_critical);
        tvTests=findViewById(R.id.tv_c_tested);
        tvName=findViewById(R.id.tv_c_name);
        recyclerView=findViewById(R.id.rv_data);
    }

    private void setUpSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()){
                    getGlobalData();
                }
                else {
                    Toast.makeText(MainActivity.this, "" + query, Toast.LENGTH_SHORT).show();
                    getCountryData(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void getCountryData(String query) {
        Call<CovidDTO> call = jsonPlaceHolderApi.getCountryData(query);
        call.enqueue(new Callback<CovidDTO>() {

            @Override
            public void onResponse(Call<CovidDTO> call, Response<CovidDTO> response) {
                if (!response.isSuccessful()){
                    getGlobalData();
                    return;
                }
                CovidDTO dto = response.body();
                if (dto != null) {
                    tvName.setText(dto.getCountry());
                    tvCritical.setText(String.valueOf(dto.getCritical()));
                    tvTotalCases.setText(String.valueOf(dto.getCases()));
                    tvTodayCases.setText(String.valueOf(dto.getTodayCases()));
                    tvDeaths.setText(String.valueOf(dto.getDeaths()));
                    tvTodayDeaths.setText(String.valueOf(dto.getTodayDeaths()));
                    tvRecovered.setText(String.valueOf(dto.getRecovered()));
                    tvTests.setText(String.valueOf(dto.getTests()));
                    tvActive.setText(String.valueOf(dto.getActive()));

                }
            }

            @Override
            public void onFailure(Call<CovidDTO> call, Throwable t) {
            getGlobalData();
            }
        });

    }

    private void getGlobalData() {
        Call<CovidDTO> call = jsonPlaceHolderApi.getCovidData();
        call.enqueue(new Callback<CovidDTO>() {

            @Override
            public void onResponse(Call<CovidDTO> call, Response<CovidDTO> response) {
                if (!response.isSuccessful()){

                    return;
                }
                CovidDTO dto = response.body();
                if (dto != null) {
                    tvName.setText(getResources().getString(R.string.global));
                    tvCritical.setText(String.valueOf(dto.getCritical()));
                    tvTotalCases.setText(String.valueOf(dto.getCases()));
                    tvTodayCases.setText(String.valueOf(dto.getTodayCases()));
                    tvDeaths.setText(String.valueOf(dto.getDeaths()));
                    tvTodayDeaths.setText(String.valueOf(dto.getTodayDeaths()));
                    tvRecovered.setText(String.valueOf(dto.getRecovered()));
                    tvTests.setText(String.valueOf(dto.getTests()));
                    tvActive.setText(String.valueOf(dto.getActive()));

                }
            }

            @Override
            public void onFailure(Call<CovidDTO> call, Throwable t) {

            }
        });
    }

    private void getCountriesData() {
        Call<List<CountryDataDTO>> listCall =jsonPlaceHolderApi.getCountriesData();
        listCall.enqueue(new Callback<List<CountryDataDTO>>() {
            @Override
            public void onResponse(Call<List<CountryDataDTO>> call, Response<List<CountryDataDTO>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<CountryDataDTO> dataDTO= response.body();
                if (dataDTO != null) {
                    dtoArrayList.addAll(dataDTO);
                    setUpAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<CountryDataDTO>> call, Throwable t) {

            }
        });

    }
    private void setUpAdapter(){
    adapter=new CountriesDataAdapter(dtoArrayList,this);
    recyclerView.setLayoutManager(gridLayoutManager);
    recyclerView.setAdapter(adapter);
    }
}
