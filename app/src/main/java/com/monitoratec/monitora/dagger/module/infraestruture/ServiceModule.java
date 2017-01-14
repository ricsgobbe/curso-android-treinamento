package com.monitoratec.monitora.dagger.module.infraestruture;

import com.monitoratec.monitora.infraestructure.storage.service.GitHubOAuthService;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubService;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubStatusService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import static com.monitoratec.monitora.dagger.module.infraestruture.NetworkModule.RETROFIT_GITHUB;
import static com.monitoratec.monitora.dagger.module.infraestruture.NetworkModule.RETROFIT_GITHUB_OAUTH;
import static com.monitoratec.monitora.dagger.module.infraestruture.NetworkModule.RETROFIT_GITHUB_STATUS;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    GitHubService providesGitHub(
            @Named(RETROFIT_GITHUB) Retrofit retrofit) {
        return retrofit.create(GitHubService.class);
    }

    @Singleton
    @Provides
    GitHubStatusService providesGitHubStatus(
            @Named(RETROFIT_GITHUB_STATUS) Retrofit retrofit) {
        return retrofit.create(GitHubStatusService.class);
    }

    @Singleton
    @Provides
    GitHubOAuthService providesGitHubOAuth(
            @Named(RETROFIT_GITHUB_OAUTH) Retrofit retrofit) {
        return retrofit.create(GitHubOAuthService.class);
    }

}
