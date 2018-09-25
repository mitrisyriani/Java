
package com.example.shobhit.findrestaurant.response.com;

import com.example.shobhit.findrestaurant.response.com.Conditions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("conditions")
    @Expose
    public Conditions conditions;
    @SerializedName("result")
    @Expose
    public String result;

}
