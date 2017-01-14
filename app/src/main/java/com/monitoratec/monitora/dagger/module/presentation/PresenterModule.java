package com.monitoratec.monitora.dagger.module.presentation;

import com.monitoratec.monitora.dagger.scope.PerActivity;
import com.monitoratec.monitora.domain.entity.repository.GitHubOAuthRepository;
import com.monitoratec.monitora.domain.entity.repository.GitHubRepository;
import com.monitoratec.monitora.domain.entity.repository.GitHubStatusRepository;
import com.monitoratec.monitora.presentation.ui.auth.AuthContract;
import com.monitoratec.monitora.presentation.ui.auth.AuthPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

@Module
public class PresenterModule {

    @PerActivity
    @Provides
    AuthContract.Presenter provideMainPresenter(
            GitHubRepository gitHubRepository,
            GitHubStatusRepository gitHubStatusRepository,
            GitHubOAuthRepository gitHubOAuthRepository) {
        return new AuthPresenter(gitHubRepository,
                gitHubStatusRepository,
                gitHubOAuthRepository);
    }
}
