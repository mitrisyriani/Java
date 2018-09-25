
package com.example.shobhit.findrestaurant;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Conditions {

    @SerializedName("wind_gust_mph")
    @Expose
    public int windGustMph;
    @SerializedName("temp_f")
    @Expose
    public float tempF;
    @SerializedName("observation_location")
    @Expose
    public ObservationLocation observationLocation;
    @SerializedName("temp_c") //needed
    @Expose
    public float tempC;
    @SerializedName("relative_humidity")
    @Expose
    public String relativeHumidity;
    @SerializedName("weather")
    @Expose
    public String weather;
    @SerializedName("dewpoint_c")
    @Expose
    public int dewpointC; //needed
    @SerializedName("windchill_c")
    @Expose
    public String windchillC; //needed
    @SerializedName("pressure_mb")
    @Expose
    public String pressureMb; //didn't use it
    @SerializedName("windchill_f")
    @Expose
    public String windchillF; //didn't use it
    @SerializedName("dewpoint_f")
    @Expose
    public int dewpointF;
    @SerializedName("wind_mph")
    @Expose
    public float windMph;

}
