package com.monitoratec.monitora.domain.entity;

/**
 * Created by ricardo.sgobbe on 11/01/2017.
 */

public class AccessToken {

    public String access_token;
    public String token_type;

    public String getAuthCredential() {
        final char firstChar = token_type.charAt(0);
        if (!Character.isUpperCase(firstChar)) {
            final String first = Character.toString(firstChar).toUpperCase();
            token_type = first + token_type.substring(1);
        }
        return token_type + " " + access_token;
    }
}
