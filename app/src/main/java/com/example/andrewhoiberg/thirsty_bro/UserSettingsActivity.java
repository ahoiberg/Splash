package com.example.andrewhoiberg.thirsty_bro;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.content.SharedPreferences;

public class UserSettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_user_settings, menu);
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

    @Override
    public void onPause(){
        super.onPause();
        saveSettings();
    }

    public void onSaveSettings(View view) {
        saveSettings();
    }

    private int getIntSetting(int id){
        String s =((EditText)findViewById(id)).getText().toString();
        int i=0;
        try {
            i = Integer.parseInt(s);
        }catch(RuntimeException e){

        }
        return i;
    }

    private void setStringSetting(int id, int s){
        ((EditText)findViewById(id)).setText(""+s);
    }


    public void saveSettings(){
        Log.d("TB", "Saved!");
        SharedPreferences settings = getSharedPreferences(UserPreferences.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        int age = getIntSetting(R.id.AgeYr);
        int weight = getIntSetting(R.id.WeightLb);
        int height = getIntSetting(R.id.HeightFt)*12+getIntSetting(R.id.HeightIn);
        boolean isMale =((RadioButton)findViewById(R.id.GenderMale)).isChecked();

        editor.putInt("age",age);
        editor.putInt("weight",weight);
        editor.putInt("height",height);
        editor.putBoolean("isMale",isMale);

        editor.commit();

        openBeginActivity();

    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences settings = getSharedPreferences(UserPreferences.PREFS_NAME, 0);
        if(settings.getInt("age",-1)!=-1)setStringSetting(R.id.AgeYr,settings.getInt("age",0));
        if(settings.getInt("weight",-1)!=-1)setStringSetting(R.id.WeightLb,settings.getInt("weight",0));
        if(settings.getInt("height",-1)!=-1)setStringSetting(R.id.HeightFt,settings.getInt("height",0)/12);
        if(settings.getInt("height",-1)!=-1)setStringSetting(R.id.HeightIn,settings.getInt("height",0)%12);

        ((RadioButton)findViewById(R.id.GenderMale)).setChecked(settings.getBoolean("isMale",true));
        ((RadioButton)findViewById(R.id.GenderFemale)).setChecked(!settings.getBoolean("isMale",false));

    }


    public void openBeginActivity(){
        this.startActivity(new Intent(this, BeforeRunActivity.class));
    }


}
