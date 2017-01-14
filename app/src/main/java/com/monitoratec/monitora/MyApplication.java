package com.monitoratec.monitora;

import android.app.Application;

import com.monitoratec.monitora.dagger.DaggerDiComponent;
import com.monitoratec.monitora.dagger.DiComponent;
import com.monitoratec.monitora.dagger.UiComponent;
import com.monitoratec.monitora.dagger.module.ApplicationModule;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */

public class MyApplication extends Application {

    private DiComponent mDiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDiComponent = DaggerDiComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public UiComponent getDaggerUiComponent() {
        return mDiComponent.uiComponent();
    }
}
