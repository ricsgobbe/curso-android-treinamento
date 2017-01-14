package com.monitoratec.monitora.domain.entity.repository;

import com.monitoratec.monitora.domain.entity.User;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by ricardo.sgobbe on 11/01/2017.
 */

public interface GitHubRepository {
    String BASE_URL = "https://api.github.com/";


    Observable<User> basicAuth(String credential);

}
