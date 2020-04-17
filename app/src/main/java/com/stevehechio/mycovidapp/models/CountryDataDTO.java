package com.stevehechio.mycovidapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hechio on 4/17/2020.\
 */
public class CountryDataDTO {
    private int cases;
    private int deaths;
    private int recovered;

    @SerializedName("country")
    private String country;

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public String getCountry() {
        return country;
    }
}
