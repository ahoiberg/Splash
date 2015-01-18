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
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sensoria.sensorialibrary.SAAnklet;
import com.sensoria.sensorialibrary.SAAnkletInterface;
import com.sensoria.sensorialibrary.SAFoundAnklet;

import java.util.Timer;
import java.util.TimerTask;

public class ConnectingActivity extends ActionBarActivity implements SAAnkletInterface {
    SAAnklet anklet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting);

        anklet = new SAAnklet(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        anklet.resume();
        anklet.startScan();
    }

    @Override
    protected void onPause() {
        super.onPause();

        anklet.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        anklet.disconnect();
    }


    public void onConnect(View view) {

        Log.w("SensoriaLibrary", "Connect to " + selectedCode + " " + selectedMac);
        anklet.deviceCode = selectedCode;
        anklet.deviceMac = selectedMac;
        anklet.connect();

        Toast.makeText(this,"Connecting...", Toast.LENGTH_LONG);

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
            openUserSettings();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openUserSettings(){
        this.startActivity(new Intent(this,WhileRunActivity.class));
    }

    private String selectedCode;
    private String selectedMac;

    @Override
    public void didDiscoverDevice() {

        Log.w("SensoriaLibrary", "Device Discovered!");

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, anklet.deviceDiscoveredList);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SAFoundAnklet deviceDiscovered = anklet.deviceDiscoveredList.get(position);
                selectedCode = deviceDiscovered.deviceCode;
                selectedMac = deviceDiscovered.deviceMac;

                Log.d("SensoriaLibrary", selectedCode + " " + selectedMac);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCode = null;
            }
        });
    }

    @Override
    public void didConnect() {

        Log.w("SensoriaLibrary", "Device Connected!");

        Toast.makeText(this,"Device Connected!.", Toast.LENGTH_LONG);

        AnkletPasser.anklet = anklet;

        openBeforeRunActivity();

    }

    public void openBeforeRunActivity(){
        this.startActivity(new Intent(this,BeforeRunActivity.class));
    }


    @Override
    public void didError(String message) {

        Log.e("SensoriaLibrary", message);
        Toast.makeText(this,"Connection failed.", Toast.LENGTH_LONG);
    }

    @Override
    public void didUpdateData() {


    }

}
