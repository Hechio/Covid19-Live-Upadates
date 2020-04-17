package com.stevehechio.mycovidapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hechio on 4/16/2020.\
 */
public class CovidDTO {
    private int cases;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private int active;
    private int critical;
    private int tests;
    private int affectedCountries;
    @SerializedName("country")
    private String country;

    public String getCountry() {
        return country;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public int getCritical() {
        return critical;
    }

    public int getTests() {
        return tests;
    }

    public int getAffectedCountries() {
        return affectedCountries;
    }
}
