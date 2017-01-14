package com.monitoratec.monitora.domain.entity.repository;

import com.monitoratec.monitora.domain.entity.Status;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ricardo.sgobbe on 09/01/2017.
 */

public interface GithubStatusRepository {


    String BASE_URL = "https://status.github.com/api/";

    Observable<Status> lastMessage();

}
