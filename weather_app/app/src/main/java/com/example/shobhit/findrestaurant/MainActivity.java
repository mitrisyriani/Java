package com.example.shobhit.findrestaurant;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shobhit.findrestaurant.response.RegistrationResponse;

import java.util.List;
import java.util.prefs.Preferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

	private String user_id;
    TextView t1,t2,t3,t4,t5;
    TextView [] TArray;
	public static String LOG_TAG = "MyApplication";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
         t1 = (TextView) findViewById(R.id.textView1);
         t2 = (TextView) findViewById(R.id.textView2);
         t3 = (TextView) findViewById(R.id.textView3);
         t4 = (TextView) findViewById(R.id.textView4);
         t5 = (TextView) findViewById(R.id.textView5);
         TArray = new TextView[]{t1,t2,t3,t4,t5};
        for (TextView b: TArray) {
            b.setVisibility(View.INVISIBLE);
        }


            final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (TextView b: TArray) {
                    b.setVisibility(View.VISIBLE);
                }
                // Perform action on click
            }
        });

        // add the button listener to here
		// Gets the settings, and creates a random user id if missing.
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		user_id = settings.getString("user_id", null);
		if (user_id == null) {
			// Creates a random one, and sets it.
			SecureRandomString srs = new SecureRandomString();
			user_id = srs.nextString();
			SharedPreferences.Editor e = settings.edit();
			e.putString("user_id", user_id);
			e.commit();
		}
        // Let's mock acquiring the nickname.
        String nickname = "Peter";

        // Let's register the user.
        // In truth, it may be better to keep a flag in preferences that tells us
        // whether we have already registered?

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://luca-teaching.appspot.com/weather/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)	//add logging
                .build();

        NicknameService service = retrofit.create(NicknameService.class);

        Call<WeatherResponse> queryResponseCall =
                service.registerUser();

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<WeatherResponse>() {
            @Override


            public void onResponse(Response<WeatherResponse> response) {
                Log.i(LOG_TAG, "Code is: " + response.code());
                Log.i(LOG_TAG, "The result is: " + response.body().response);
               // response.body().response.conditions.observationLocation.city
                ((TextView) findViewById(R.id.textView1)).setText("City: " + response.body().response.conditions.observationLocation.city + ", Elevation: " + response.body().response.conditions.observationLocation.elevation);
                //((TextView) findViewById(R.id.textView1)).setText("Elevation: " + response.body().response.conditions.observationLocation.elevation);
                ((TextView) findViewById(R.id.textView2)).setText("Temperature: " + response.body().response.conditions.tempF);
                ((TextView) findViewById(R.id.textView3)).setText("Relative Humidity: " + response.body().response.conditions.relativeHumidity);
                ((TextView) findViewById(R.id.textView4)).setText("Wind Average Speed: " + response.body().response.conditions.windMph + ", Wind gusts: "  + response.body().response.conditions.windGustMph);
                ((TextView) findViewById(R.id.textView5)).setText("Weather: " + response.body().response.conditions.weather);

                //Log.i(LOG_TAG, "The result is: " + response.body().response);
                // tempf shows 42.1
                // weather shows clear
                //ewlaticeHumidity shows 99
                //dewpointF shows 42
                // tempc shows 5.6
                //dewpointc shows 5
                //windchillC show 6
                //observationLOcation show com.example.sho
                //windchillF show 42
                //dewpointc show 5
                //windchillC show 6
                //pressureMB 1026
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
            }
        });

    }

//ends here
	@Override
	public void onResume(){
		super.onResume();
	}


    /**
     * Foursquare api https://developer.foursquare.com/docs/venues/search
     */
    public interface NicknameService {
        @GET("default/get_weather")
        Call<WeatherResponse> registerUser();
    }

}
