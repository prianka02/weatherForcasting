package com.example.weatherbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class forcastWeather extends AppCompatActivity {

    final String API_KEY = "a4b7aa0481908416078a7d6dee7abd3d";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    String Location_Provider = LocationManager.GPS_PROVIDER;

    TextView temparatureValue, wthertype, loginBtn;

    LocationManager mlocationManager;
    LocationListener mlocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcast_weather);

        temparatureValue = findViewById(R.id.Temperature);
        wthertype = findViewById(R.id.weatherType);



        loginBtn = findViewById(R.id.login);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forcastWeather.this, LoginForm.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

        getWeatherForCurrentLocation();

    }




//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        city.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                String newCity = city.getText().toString();
//
//                if (newCity!= null){
//
//                    getWeatherForNewCity(newCity);
//                }
//                else{
//                    getWeatherForCurrentLocation();
//                }
//                return false;
//            }
//        });
//
//        getWeatherForCurrentLocation();
//
//    }

private void getWeatherForNewCity(String newCity){

        RequestParams params = new RequestParams();
        params.put("q",newCity);
        params.put("appid",API_KEY);
        letsdoSomeNetworking(params);

}




    private void getWeatherForCurrentLocation() {

        mlocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Toast.makeText(forcastWeather.this, "do fetch to get api", Toast.LENGTH_SHORT).show();

                String Latitude = String.valueOf(location.getLatitude());
                String Longitude = String.valueOf(location.getLongitude());

                Toast.makeText(forcastWeather.this, "Latitude: "+ Latitude + "\n"+ "Longitude" + Longitude, Toast.LENGTH_LONG).show();
                RequestParams params = new RequestParams();
                params.put("lat", Latitude);
                params.put("lon", Longitude);
                params.put("appid",API_KEY);
                letsdoSomeNetworking(params);

            }

            @Override
            public void onLocationChanged(@NonNull List<Location> locations) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                LocationListener.super.onProviderEnabled(provider);
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                LocationListener.super.onProviderDisabled(provider);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        mlocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mlocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(forcastWeather.this, "Permission Accepted", Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
            }
            else {
                //user denied permission
                Toast.makeText(forcastWeather.this, "Not Accepted", Toast.LENGTH_SHORT).show();


            }
        }
    }

    private void letsdoSomeNetworking(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        Toast.makeText(forcastWeather.this, "Not now", Toast.LENGTH_SHORT).show();

        client.get(WEATHER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              //  super.onSuccess(statusCode, headers, response);

                Toast.makeText(forcastWeather.this, "Data Get Success", Toast.LENGTH_SHORT).show();

                weatherData weatherD = weatherData.fromJson(response);
                updateUI(weatherD);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
               // super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(forcastWeather.this, "Can't Access data!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void updateUI(weatherData weather){

        temparatureValue.setText(weather.getmTemperature());
        wthertype.setText(weather.getmWeatherType());
    }

    @Override
    protected void onPause() { //we doesn't want to fetch the weather again and again
        super.onPause();
        if(mlocationManager!=null){
            mlocationManager.removeUpdates(mlocationListener);
        }
    }

}