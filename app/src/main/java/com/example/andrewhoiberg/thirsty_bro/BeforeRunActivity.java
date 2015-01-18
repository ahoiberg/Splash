package com.example.andrewhoiberg.thirsty_bro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

/**
 * Created by test on 2015/01/17.
 */
public class BeforeRunActivity extends ActionBarActivity {
    SAAnklet anklet;
    final static int CONNECTION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserPreferences();
    }

    public void displayUserPreferences(){
        TextView age = (TextView) findViewById(R.id.ageValue);
        //TextView temperature = (TextView) findViewById(R.id.temperatureValue);
        //TextView location = (TextView) findViewById(R.id.locationValue);
        //TextView humidity = (TextView) findViewById(R.id.humidityValue);
        //TextView precipitation = (TextView) findViewById(R.id.precipitationValue);
        //TextView windspeed = (TextView) findViewById(R.id.windspeedValue);
        //TextView runningcondition = (TextView) findViewById(R.id.runningconditionValue);

        SharedPreferences settings = getSharedPreferences(UserPreferences.PREFS_NAME, 0);
        final  BeforeRunActivity s= this;
        Thread weatherThread = new Thread(){
          @Override
            public void run(){
              s.callWeatherAPI();
          }
        };
        weatherThread.start();

        age.setText(String.format("%d", settings.getInt("age",0)));
        //height.setText(String.format("%d", settings.getInt("height",-1)));
        //weight.setText(String.format("%d", settings.getInt("weight",0)));
        //gender.setText(settings.getBoolean("isMale",true)?"Male":"Female");
    }

    public void callWeatherAPI(){
        String latitude = "37.252194";
        String longitude = "-121.360474";
        //TODO pass coordinates here
        WeatherInfo weather = null;
        try {
            weather = WeatherProvider.getWeather(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("TB",weather.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void onConnect(View view) {
        startActivity(new Intent(this, ConnectingActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            openUserSettings();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openUserSettings() {
        this.startActivity(new Intent(this, UserSettingsActivity.class));
    }

    //@Override
    //public void didUpdateData() {

        //TextView tick = (TextView) findViewById(R.id.tickValue);
        //TextView mtb1 = (TextView) findViewById(R.id.mtb1Value);
        //TextView mtb5 = (TextView) findViewById(R.id.mtb5Value);
        //TextView heel = (TextView) findViewById(R.id.heelValue);
        //TextView accX = (TextView) findViewById(R.id.accXValue);
        //TextView accY = (TextView) findViewById(R.id.accYValue);
        //TextView accZ = (TextView) findViewById(R.id.accZValue);


        //tick.setText(String.format("%d", anklet.tick));
        //mtb1.setText(String.format("%d", anklet.mtb1));
        //mtb5.setText(String.format("%d", anklet.mtb5));
        //heel.setText(String.format("%d", anklet.heel));
        //accX.setText(String.format("%f", anklet.accX));
        //accY.setText(String.format("%f", anklet.accY));
        //accZ.setText(String.format("%f", anklet.accZ));

    //}

}
