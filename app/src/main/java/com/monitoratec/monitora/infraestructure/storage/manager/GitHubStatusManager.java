package com.monitoratec.monitora.infraestructure.storage.manager;

import com.monitoratec.monitora.domain.entity.Status;
import com.monitoratec.monitora.domain.entity.repository.GitHubStatusRepository;
import com.monitoratec.monitora.infraestructure.storage.service.GitHubStatusService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public class GitHubStatusManager implements GitHubStatusRepository {

    private GitHubStatusService mGitHubStatus;

    public GitHubStatusManager(GitHubStatusService gitHubStatusService){
        mGitHubStatus = gitHubStatusService;
    }

    @Override
    public Observable<Status> lastMessage() {
        return mGitHubStatus.lastMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
