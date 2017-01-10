package com.monitoratec.monitora.domain.entity;

import com.google.gson.annotations.SerializedName;
import com.monitoratec.monitora.R;

/**
 * Created by ricardo.sgobbe on 09/01/2017.
 */

public class Status {

    public Type  status;
    public String body;
    public String created_on;


    public enum Type{
        @SerializedName("good")
        GOOD(R.color.blue, "good"),
        @SerializedName("minor")
        MINOR(R.color.orange, "minor"),
        @SerializedName("major")
        MAJOR(R.color.red, "major");

        int color;
        String statusName;

        Type(int color, String statusName){
            this.color = color;
            this.statusName = statusName;
        }

        public int getColor(){
            return color;
        }

        public String getStatusName(){
            return statusName;
        }
    }


}
