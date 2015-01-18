package com.example.andrewhoiberg.thirsty_bro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MapsActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);

    }
    // Acquire a reference to the system Location Manager
    LocationManager  locationManager = null;

    final MapsActivity sp = this;

    public LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location slocation) {
            Log.v("TB",slocation.getLatitude()+" | "+slocation.getLongitude());
            final Location location = slocation;
            Thread thread = new Thread() {
                public void run() {
                    WeatherInfo weather = WeatherProvider.getWeather("" + location.getLatitude(), "" + location.getLongitude());

                    URL url = null;
                    try {
                        url = new URL(weather.getIconURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    Bitmap bmp = null;
                    try {
                        bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    sp.useWeatherData(weather,bmp);

                }
            };
            thread.start();
        }


        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }

    };

    public void useWeatherData(WeatherInfo weather,Bitmap weatherIcon){

    }


}