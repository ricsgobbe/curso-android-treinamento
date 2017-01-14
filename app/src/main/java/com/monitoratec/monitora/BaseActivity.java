package com.monitoratec.monitora;

import android.support.v7.app.AppCompatActivity;

import com.monitoratec.monitora.dagger.DiComponent;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected MyApplication getMyApplication(){
        return ((MyApplication)getApplication());
    }


    protected DiComponent getDaggerDiComponent(){
        return ((MyApplication)getApplication()).getDaggerDiComponent();
    }

}
