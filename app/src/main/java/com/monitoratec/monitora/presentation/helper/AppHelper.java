package com.monitoratec.monitora.presentation.helper;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;

import com.monitoratec.monitora.R;

/**
 * Created by ricardo.sgobbe on 11/01/2017.
 */

public final class AppHelper {

    private AppHelper(){}

    public static boolean vailidateRequiredField(Context context, TextInputLayout...fields){
        boolean isValid = true;
        for(TextInputLayout field : fields){
            EditText editText = field.getEditText();
            if(editText != null){
                if(TextUtils.isEmpty(editText.getText())){
                    field.setErrorEnabled(true);
                    field.setError(context.getString(R.string.txt_required));
                    isValid = false;
                }else{
                    field.setErrorEnabled(false);
                    field.setError(null);
                }
            }else{
                throw new RuntimeException("TextInputLayout must have a EditText");
            }
        }
        return isValid;
    }

}
