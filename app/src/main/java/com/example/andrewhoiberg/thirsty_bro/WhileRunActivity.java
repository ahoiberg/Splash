package com.example.andrewhoiberg.thirsty_bro;

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
/**
 * Created by test on 2015/01/17.
 */
public class WhileRunActivity extends ActionBarActivity{
    SAAnklet anklet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        anklet.resume();
        displayUserPreferences();
    }

    public void displayUserPreferences(){
        //TextView age = (TextView) findViewById(R.id.ageValue);
        //TextView height = (TextView) findViewById(R.id.heightValue);
        //TextView weight = (TextView) findViewById(R.id.weightValue);
        //TextView gender = (TextView) findViewById(R.id.genderValue);
        //TextView gender = (TextView) findViewById(R.id.genderValue);
        //TextView gender = (TextView) findViewById(R.id.genderValue);

        //SharedPreferences settings = getSharedPreferences(UserPreferences.PREFS_NAME, 0);

        //age.setText(String.format("%d", settings.getInt("age",0)));
        //height.setText(String.format("%d", settings.getInt("height",-1)));
        //weight.setText(String.format("%d", settings.getInt("weight",0)));
        //gender.setText(settings.getBoolean("isMale",true)?"Male":"Female");
    }

}
