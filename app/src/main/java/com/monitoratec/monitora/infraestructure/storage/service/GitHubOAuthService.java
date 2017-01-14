package com.monitoratec.monitora.infraestructure.storage.service;

import com.monitoratec.monitora.domain.entity.AccessToken;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ricardo.sgobbe on 11/01/2017.
 */

public interface GitHubOAuthService {
    String BASE_URL = "https://github.com/login/oauth/";


    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("access_token")
    Observable<AccessToken> accessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);
}
