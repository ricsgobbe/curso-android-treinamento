package com.monitoratec.monitora.domain.entity.repository;

import com.monitoratec.monitora.domain.entity.AccessToken;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ricardo.sgobbe on 11/01/2017.
 */

public interface GitHubOAuthRepository {
    String BASE_URL = "https://github.com/login/oauth/";


    Observable<AccessToken> accessToken(
           String clientId,
           String clientSecret,
           String code);
}
