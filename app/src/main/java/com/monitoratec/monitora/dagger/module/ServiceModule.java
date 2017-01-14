package com.monitoratec.monitora.dagger.module;

import com.monitoratec.monitora.domain.entity.GitHubApi;
import com.monitoratec.monitora.domain.entity.GitHubOAuthApi;
import com.monitoratec.monitora.domain.entity.GithubStatusApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import static com.monitoratec.monitora.dagger.module.NetworkModule.RETROFIT_GITHUB;
import static com.monitoratec.monitora.dagger.module.NetworkModule.RETROFIT_GITHUB_OAUTH;
import static com.monitoratec.monitora.dagger.module.NetworkModule.RETROFIT_GITHUB_STATUS;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    GitHubApi providesGitHub(
            @Named(RETROFIT_GITHUB) Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

    @Singleton
    @Provides
    GithubStatusApi providesGitHubStatus(
            @Named(RETROFIT_GITHUB_STATUS) Retrofit retrofit) {
        return retrofit.create(GithubStatusApi.class);
    }

    @Singleton
    @Provides
    GitHubOAuthApi providesGitHubOAuth(
            @Named(RETROFIT_GITHUB_OAUTH) Retrofit retrofit) {
        return retrofit.create(GitHubOAuthApi.class);
    }

}
