package com.monitoratec.monitora.infraestructure.storage.manager;

import com.monitoratec.monitora.domain.entity.AccessToken;
import com.monitoratec.monitora.domain.entity.repository.GitHubOAuthRepository;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubOAuthService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public class GitHubOAuthManager implements GitHubOAuthRepository {

    private GitHubOAuthService mGitHubOAuth;

    public GitHubOAuthManager(GitHubOAuthService gitHubOAuthService){
        mGitHubOAuth = gitHubOAuthService;
    }

    @Override
    public Observable<AccessToken> accessToken(String clientId, String clientSecret, String code) {
        return mGitHubOAuth.accessToken(clientId, clientSecret, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
