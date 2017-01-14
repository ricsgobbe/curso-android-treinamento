package com.monitoratec.monitora.presentation.base;

import android.support.v7.app.AppCompatActivity;

import com.monitoratec.monitora.MyApplication;
import com.monitoratec.monitora.dagger.DiComponent;
import com.monitoratec.monitora.dagger.UiComponent;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected MyApplication getMyApplication(){
        return ((MyApplication)getApplication());
    }


    protected UiComponent getDaggerDiComponent(){
        return ((MyApplication)getApplication()).getDaggerUiComponent();
    }

}
