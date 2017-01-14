package com.monitoratec.monitora.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.monitoratec.monitora.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */

@Module
public class PreferenceModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        final String fileName = context.getString(R.string.sp_file_key);
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }


}
