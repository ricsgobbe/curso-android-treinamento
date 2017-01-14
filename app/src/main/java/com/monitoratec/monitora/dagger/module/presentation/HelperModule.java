package com.monitoratec.monitora.dagger.module.presentation;

import android.content.Context;

import com.monitoratec.monitora.presentation.helper.AppHelper;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

@Module
public class HelperModule {

    @Provides
    @Reusable
    AppHelper provideTextHelper(Context context) {
        return new AppHelper(context);
    }

}
