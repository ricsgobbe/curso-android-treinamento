package com.monitoratec.monitora.dagger.module.infraestruture;

import com.monitoratec.monitora.domain.entity.repository.GitHubOAuthRepository;
import com.monitoratec.monitora.domain.entity.repository.GitHubRepository;
import com.monitoratec.monitora.domain.entity.repository.GitHubStatusRepository;
import com.monitoratec.monitora.infraestructure.storage.manager.GitHubManager;
import com.monitoratec.monitora.infraestructure.storage.manager.GitHubOAuthManager;
import com.monitoratec.monitora.infraestructure.storage.manager.GitHubStatusManager;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubOAuthService;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubService;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubStatusService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

@Module
public class ManagerModule {

    @Singleton
    @Provides
    GitHubRepository providesGitHubRepository(
            GitHubService gitHubService) {
        return new GitHubManager(gitHubService);
    }

    @Singleton
    @Provides
    GitHubStatusRepository providesGitHubStatusRepository(
            GitHubStatusService gitHubStatusService) {
        return new GitHubStatusManager(gitHubStatusService);
    }

    @Singleton
    @Provides
    GitHubOAuthRepository providesGitHubOAuthRepository(
            GitHubOAuthService gitHubOAuthService) {
        return new GitHubOAuthManager(gitHubOAuthService);
    }
}
