package com.example.spisok;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.lang.String;

public class Contributor {
    private String login;
    private int contributions;

    // И другие поля
    //String "html_url";

    @Override
    public String toString() {
        return "                    " + login + ";" + contributions;
    }

}
