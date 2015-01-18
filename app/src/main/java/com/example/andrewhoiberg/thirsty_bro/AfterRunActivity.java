package com.example.andrewhoiberg.thirsty_bro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class AfterRunActivity extends ActionBarActivity {
    SAAnklet anklet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ((TextView)findViewById(R.id.proValue)).setText(""+AnkletPasser.pronation);
        ((TextView)findViewById(R.id.mlValue)).setText(""+AnkletPasser.mililiters+" mL");
        ((TextView)findViewById(R.id.tstValue)).setText(""+AnkletPasser.steps);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onBeforeRun(View view) {
        openBeforeRun();
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

        if (id == R.id.connect_settings) {
            openConnectionSettings();
        }

        if (id == R.id.before_run) {
            openBeforeRun();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openUserSettings(){
        this.startActivity(new Intent(this,UserSettingsActivity.class));
    }

    public void openBeforeRun(){
        this.startActivity(new Intent(this,BeforeRunActivity.class));
    }

    final static int CONNECTION_REQUEST_CODE=1;
    public void openConnectionSettings(){
        this.startActivity(new Intent(this,ConnectingActivity.class));
        Intent connectionSettingsIntent = new Intent(this,ConnectingActivity.class);
        startActivityForResult(connectionSettingsIntent, CONNECTION_REQUEST_CODE);
    }

}