package com.example.andrewhoiberg.thirsty_bro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sensoria.sensoriadroidjni.SignalProcessing;
import com.sensoria.sensorialibrary.SAAnklet;
import com.sensoria.sensorialibrary.SAAnkletInterface;
import com.sensoria.sensorialibrary.SAFoundAnklet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by test on 2015/01/17.
 */
public class WhileRunActivity extends MapsActivity {
    SAAnklet anklet;
    SignalProcessing signalProcessing;

    long startMin=0;

    WeatherInfo weatherData=null;
    Bitmap weatherIcon = null;

    @Override
    public void useWeatherData(WeatherInfo weather,Bitmap weatherIcon){
        weatherData=weather;
        this.weatherIcon = weatherIcon;
        Log.d("TB","GOT WEATHER DATA");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_while);
        signalProcessing = new SignalProcessing();
        startMin = getMin();

        anklet=AnkletPasser.anklet;
        anklet.connect();

    }

    @Override
    protected void onResume() {
        super.onResume();
        final WhileRunActivity st = this;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                st.updateData();
            }
        };
        t.scheduleAtFixedRate(tt, 50L, 50L);


    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onAfterRun(View view) {
        startActivity(new Intent(this, AfterRunActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void updateData() {
        new WaterTask().execute(this);
    }


    private class WaterTask extends AsyncTask<WhileRunActivity, Integer, Long> {
        WhileRunActivity a=null;
        protected Long doInBackground(WhileRunActivity... activities) {
            a = activities[0];
            return 1L;
        }


        @Override
        protected void onPostExecute(Long l){
            Log.d("TB","UPDATE! "+anklet.tick);
            if(anklet.tick==0){
                anklet.connect();
            }
            if(anklet.tick>0) {

                double[] rawDataBuffer = new double[6];
                rawDataBuffer[0] = (double) anklet.mtb5;
                rawDataBuffer[1] = (double) anklet.mtb1;
                rawDataBuffer[2] = (double) anklet.heel;
                rawDataBuffer[3] = (double) anklet.accX;
                rawDataBuffer[4] = (double) anklet.accY;
                rawDataBuffer[5] = (double) anklet.accZ;

                signalProcessing.processIncomingData(rawDataBuffer);
                //Log.e("Steps", String.format("%d", (int) signalProcessing.getStepCount()));

                SharedPreferences settings = getSharedPreferences(UserPreferences.PREFS_NAME, 0);

                TextView steps = ((TextView) findViewById(R.id.stepsValue));
                TextView cadence = ((TextView) findViewById(R.id.cadenceValue));
                TextView waterValue = ((TextView) findViewById(R.id.waterValue));

                steps.setText("" + (int) signalProcessing.getStepCount());
                cadence.setText("" + (int) signalProcessing.getCadence());

                ImageView water = ((ImageView) findViewById(R.id.water));

                //if (weatherData != null) {
                    int mililiters = (int) signalProcessing.getStepCount()*10;//((int)DehydrationCalculator.CalculateRecommendedLiters((int) signalProcessing.getStepCount(),
                            //settings.getInt("weight", -1), (int) weatherData.getTemperatureF(), (int) (getMin() - startMin), settings.getBoolean("isMale", true) ? DehydrationCalculator.Gender.MALE : DehydrationCalculator.Gender.FEMALE)/1000);

                    int liters = mililiters/100;


                    if (liters < 0) liters = 0;
                    if (liters > 10) liters = 10;

                    waterValue.setText("" + mililiters);

                    switch(liters){
                        case 0:
                            water.setImageResource(R.drawable.water0);
                            break;
                        case 1:
                            water.setImageResource(R.drawable.water1);
                                    break;
                        case 2:
                            water.setImageResource(R.drawable.water2);
                            break;
                        case 3:
                            water.setImageResource(R.drawable.water3);
                            break;
                        case 4:
                            water.setImageResource(R.drawable.water4);
                            break;
                        case 5:
                            water.setImageResource(R.drawable.water5);
                            break;
                        case 6:
                            water.setImageResource(R.drawable.water6);
                            break;
                        case 7:
                            water.setImageResource(R.drawable.water7);
                            break;
                        case 8:
                            water.setImageResource(R.drawable.water8);
                            break;
                        case 9:
                            water.setImageResource(R.drawable.water9);
                            break;
                        case 10:
                            water.setImageResource(R.drawable.water10);
                            break;
                    }
                //}
            }

        }

    }
    }
