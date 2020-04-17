package com.stevehechio.mycovidapp.utils;

import com.stevehechio.mycovidapp.models.CountryDataDTO;
import com.stevehechio.mycovidapp.models.CovidDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hechio on 4/16/2020.\
 */
public interface JSONPlaceHolderApi {
    @GET("v2/all")
    Call<CovidDTO> getCovidData();

    @GET("/v2/countries")
    Call<List<CountryDataDTO>> getCountriesData();

    @GET("/v2/countries/{country}")
    Call<CovidDTO> getCountryData(@Path("country") String countryName);
}
