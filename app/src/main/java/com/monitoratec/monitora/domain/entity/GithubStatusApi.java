package com.monitoratec.monitora.domain.entity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ricardo.sgobbe on 09/01/2017.
 */

public interface GithubStatusApi {


    String BASE_URL = "https://status.github.com/api/";

    @GET("last-message.json")
    Call<Status> lastMessage();

}
