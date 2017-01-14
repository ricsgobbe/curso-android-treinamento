package com.monitoratec.monitora.dagger;

import com.monitoratec.monitora.presentation.ui.auth.AuthActivity;
import com.monitoratec.monitora.dagger.module.ApplicationModule;
import com.monitoratec.monitora.dagger.module.PreferenceModule;
import com.monitoratec.monitora.dagger.module.infraestruture.*;
import com.monitoratec.monitora.dagger.module.presentation.*;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */


@Singleton
@Component(modules = {
        ApplicationModule.class,
        HelperModule.class,
        PreferenceModule.class,
        NetworkModule.class,
        ServiceModule.class,
        ManagerModule.class
})
public interface DiComponent {
    UiComponent uiComponent();
}
