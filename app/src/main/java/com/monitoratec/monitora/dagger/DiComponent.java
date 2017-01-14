package com.monitoratec.monitora.dagger;

import com.monitoratec.monitora.MainActivity;
import com.monitoratec.monitora.dagger.module.ApplicationModule;
import com.monitoratec.monitora.dagger.module.NetworkModule;
import com.monitoratec.monitora.dagger.module.PreferenceModule;
import com.monitoratec.monitora.dagger.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */


   @Singleton
@Component(modules = {
        ApplicationModule.class,
        PreferenceModule.class,
        NetworkModule.class,
        ServiceModule.class
})
public interface DiComponent {

    void inject(MainActivity activity);

}
