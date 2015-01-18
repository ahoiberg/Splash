package com.example.andrewhoiberg.thirsty_bro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sensoria.sensorialibrary.SAAnklet;
import com.sensoria.sensorialibrary.SAAnkletInterface;
import com.sensoria.sensorialibrary.SAFoundAnklet;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by test on 2015/01/17.
 */
public class BeforeRunActivity extends MapsActivity {
    SAAnklet anklet;
    final static int CONNECTION_REQUEST_CODE=1;
    WeatherInfo weatherData=null;
    Bitmap weatherIcon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
    }

    @Override
    public void useWeatherData(WeatherInfo weather,Bitmap weatherIcon){
        weatherData=weather;
        this.weatherIcon = weatherIcon;
        Log.d("TB","GOT WEATHER DATA");
    }


    @Override
    protected void onResume() {
        super.onResume();
        displayUserPreferences();
        displayConnection();
        new WeatherTask().execute(this);
    }

    public void displayConnection(){
        Button b = ((Button)findViewById(R.id.connect));
        b.setText(R.string.connect_settings);
    }
    public void displayUserPreferences(){
        TextView age = (TextView) findViewById(R.id.ageValue);
        //TextView temperature = (TextView) findViewById(R.id.temperatureValue);
        //TextView location = (TextView) findViewById(R.id.locationValue);
        //TextView humidity = (TextView) findViewById(R.id.humidityValue);
        //TextView precipitation = (TextView) findViewById(R.id.precipitationValue);
        //TextView windspeed = (TextView) findViewById(R.id.windspeedValue);
        //TextView runningcondition = (TextView) findViewById(R.id.runningconditionValue);


        //age.setText(String.format("%d", settings.getInt("age",0)));

        //height.setText(String.format("%d", settings.getInt("height",-1)));
        //weight.setText(String.format("%d", settings.getInt("weight",0)));
        //gender.setText(settings.getBoolean("isMale",true)?"Male":"Female");
    }



    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void onUserSettings(View view){
        startActivity(new Intent(this, UserSettingsActivity.class));
    }

    public void onConnect(View view) {
        startActivity(new Intent(this, ConnectingActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openConnectingActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openConnectingActivity() {
        this.startActivity(new Intent(this, ConnectingActivity.class));

    }

    private class WeatherTask extends AsyncTask<BeforeRunActivity, Integer, Long> {
        BeforeRunActivity a=null;
        protected Long doInBackground(BeforeRunActivity... activities) {
            a = activities[0];
            while(a.weatherData==null){

            }
            return 1L;
        }

        @Override
        protected void onPostExecute(Long l){
            ((TextView) findViewById(R.id.locationValue)).setText(a.weatherData.getLocation());
            ((TextView) findViewById(R.id.humidityValue)).setText(a.weatherData.getHumidity());
            ((TextView) findViewById(R.id.temperatureValue)).setText(a.weatherData.getTemperatureF()+" F");
            ((TextView) findViewById(R.id.precipitationValue)).setText(a.weatherData.getPrecipitationHrIn()+" Hr/In");
            ((TextView) findViewById(R.id.windspeedValue)).setText(a.weatherData.getWindDescription());


            ((ImageView) findViewById(R.id.weatherIcon)).setImageBitmap(a.weatherIcon);

        }

    }
}

