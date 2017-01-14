package com.monitoratec.monitora.presentation.ui.auth;

import com.monitoratec.monitora.domain.entity.Status;
import com.monitoratec.monitora.domain.entity.User;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public interface AuthContract {


    interface View {
        void onLoadStatusType(Status.Type statusType);

        void onAuthSuccess(String credential, User entity);

        void showError(String message);
    }

    interface Presenter {
        void setView(AuthContract.View view);

        void loadStatus();

        void callGetUser(String authorization);

        void callAccessToken(String cliId, String cliSecret, String code);
    }

}
