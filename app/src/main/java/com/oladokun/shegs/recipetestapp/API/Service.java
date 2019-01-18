package com.oladokun.shegs.recipetestapp.API;

import com.oladokun.shegs.recipetestapp.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/Nsikaktopdown/Recipe/master/Recipe.json")
    Call<List<Recipe>> readJson();
}
