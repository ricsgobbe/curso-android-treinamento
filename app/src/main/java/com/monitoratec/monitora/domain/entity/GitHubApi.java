package com.monitoratec.monitora.domain.entity;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by ricardo.sgobbe on 11/01/2017.
 */

public interface GitHubApi {
    String BASE_URL = "https://api.github.com/";


    @GET("user")
    Observable<User> basicAuth(@Header("Authorization") String credential);

}
