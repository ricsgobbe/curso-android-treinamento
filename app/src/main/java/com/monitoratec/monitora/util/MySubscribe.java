package com.monitoratec.monitora.util;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by ricardo.sgobbe on 12/01/2017.
 */

public abstract class MySubscribe<T> extends Subscriber<T> {

    private static final String TAG = MySubscribe.class.getSimpleName();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage(), e);
        onError(e.getMessage());
    }

    public abstract void onError(String message);

}
