package com.monitoratec.monitora.infraestructure.storage.service;

import com.monitoratec.monitora.domain.entity.Status;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ricardo.sgobbe on 09/01/2017.
 */

public interface GithubStatusService {


    String BASE_URL = "https://status.github.com/api/";

    @GET("last-message.json")
    Observable<Status> lastMessage();

}
