package com.example.andrewhoiberg.thirsty_bro;

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


public class MainActivity extends ActionBarActivity implements SAAnkletInterface {

    SAAnklet anklet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anklet = new SAAnklet(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        anklet.resume();
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

    public void onStartScan(View view) {
        anklet.startScan();
    }

    public void onStopScan(View view) {
        anklet.stopScan();
    }

    public void onConnect(View view) {

        Log.w("SensoriaLibrary", "Connect to " + selectedCode + " " + selectedMac);
        anklet.deviceCode = selectedCode;
        anklet.deviceMac = selectedMac;
        anklet.connect();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    }

    @Override
    public void didError(String message) {

        Log.e("SensoriaLibrary", message);

    }

    @Override
    public void didUpdateData() {

        TextView tick = (TextView) findViewById(R.id.tickValue);
        TextView mtb1 = (TextView) findViewById(R.id.mtb1Value);
        TextView mtb5 = (TextView) findViewById(R.id.mtb5Value);
        TextView heel = (TextView) findViewById(R.id.heelValue);
        TextView accX = (TextView) findViewById(R.id.accXValue);
        TextView accY = (TextView) findViewById(R.id.accYValue);
        TextView accZ = (TextView) findViewById(R.id.accZValue);

        tick.setText(String.format("%d", anklet.tick));
        mtb1.setText(String.format("%d", anklet.mtb1));
        mtb5.setText(String.format("%d", anklet.mtb5));
        heel.setText(String.format("%d", anklet.heel));
        accX.setText(String.format("%f", anklet.accX));
        accY.setText(String.format("%f", anklet.accY));
        accZ.setText(String.format("%f", anklet.accZ));
    }

    public static class MapsActivity extends FragmentActivity {

        private GoogleMap mMap; // Might be null if Google Play services APK is not available.

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(com.example.andrewhoiberg.thirsty_bro.R.layout.activity_maps);
            setUpMapIfNeeded();
        }

        @Override
        protected void onResume() {
            super.onResume();
            setUpMapIfNeeded();
        }

        /**
         * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
         * installed) and the map has not already been instantiated.. This will ensure that we only ever
         * call {@link #setUpMap()} once when {@link #mMap} is not null.
         * <p/>
         * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
         * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
         * install/update the Google Play services APK on their device.
         * <p/>
         * A user can return to this FragmentActivity after following the prompt and correctly
         * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
         * have been completely destroyed during this process (it is likely that it would only be
         * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
         * method in {@link #onResume()} to guarantee that it will be called.
         */
        private void setUpMapIfNeeded() {
            // Do a null check to confirm that we have not already instantiated the map.
            if (mMap == null) {
                // Try to obtain the map from the SupportMapFragment.
                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(com.example.andrewhoiberg.thirsty_bro.R.id.map))
                        .getMap();
                // Check if we were successful in obtaining the map.
                if (mMap != null) {
                    setUpMap();
                }
            }
        }

        /**
         * This is where we can add markers or lines, add listeners or move the camera. In this case, we
         * just add a marker near Africa.
         * <p/>
         * This should only be called once and when we are sure that {@link #mMap} is not null.
         */
        private void setUpMap() {
            mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        }


    }
}
