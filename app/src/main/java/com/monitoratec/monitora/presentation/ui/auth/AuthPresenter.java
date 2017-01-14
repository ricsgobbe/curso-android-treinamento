package com.monitoratec.monitora.presentation.ui.auth;

import com.monitoratec.monitora.domain.entity.Status;
import com.monitoratec.monitora.domain.entity.repository.GitHubOAuthRepository;
import com.monitoratec.monitora.domain.entity.repository.GitHubRepository;
import com.monitoratec.monitora.domain.entity.repository.GitHubStatusRepository;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public class AuthPresenter implements AuthContract.Presenter {
    private AuthContract.View mView;
    private GitHubRepository mGitHubRepository;
    private GitHubStatusRepository mGitHubStatusRepository;
    private GitHubOAuthRepository mGitHubOAuthRepository;

    public AuthPresenter(GitHubRepository gitHubRepository,
                         GitHubStatusRepository gitHubStatusRepository,
                         GitHubOAuthRepository gitHubOAuthRepository) {
        mGitHubRepository = gitHubRepository;
        mGitHubStatusRepository = gitHubStatusRepository;
        mGitHubOAuthRepository = gitHubOAuthRepository;
    }

    @Override
    public void setView(AuthContract.View view) {
        mView = view;
    }

    @Override
    public void loadStatus() {
        mGitHubStatusRepository.lastMessage()
                .subscribe(entity -> {
                    mView.onLoadStatusType(entity.status);
                }, error -> {
                    mView.onLoadStatusType(Status.Type.MAJOR);
                });
    }

    @Override
    public void callGetUser(String authorization) {
        mGitHubRepository.basicAuth(authorization)
                .subscribe(entity -> {
                    mView.onAuthSuccess(authorization, entity);
                }, error -> {
                    mView.showError(error.getMessage());
                });
    }

    @Override
    public void callAccessToken(String clientId,
                                String clientSecret,
                                String code) {
        mGitHubOAuthRepository.accessToken(clientId, clientSecret, code)
                .subscribe(entity -> {
                    callGetUser(entity.getAuthCredential());
                }, error -> {
                    mView.showError(error.getMessage());
                });
    }
}
