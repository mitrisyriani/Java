
package com.example.shobhit.findrestaurant.response.com;



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
    @SerializedName("temp_c")
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
    public int dewpointC;
    @SerializedName("windchill_c")
    @Expose
    public String windchillC;
    @SerializedName("pressure_mb")
    @Expose
    public String pressureMb;
    @SerializedName("windchill_f")
    @Expose
    public String windchillF;
    @SerializedName("dewpoint_f")
    @Expose
    public int dewpointF;
    @SerializedName("wind_mph")
    @Expose
    public float windMph;

}
