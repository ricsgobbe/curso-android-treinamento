package com.monitoratec.monitora.infraestructure.storage.manager;

import com.monitoratec.monitora.domain.entity.User;
import com.monitoratec.monitora.domain.entity.repository.GitHubRepository;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public class GitHubManager implements GitHubRepository {

    private GitHubService mGitHubService;

    public GitHubManager(GitHubService gitHubService){
        mGitHubService = gitHubService;
    }

    @Override
    public Observable<User> basicAuth(String credential) {
        return mGitHubService.basicAuth(credential)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
