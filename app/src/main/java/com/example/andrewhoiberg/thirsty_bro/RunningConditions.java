package com.example.andrewhoiberg.thirsty_bro;

/**
 * Created by andrewhoiberg on 1/18/15.
 */
public class RunningConditions {

    private static int RunningThreshold = 10;
    private static int HumidityFactor = 20;
    private static int PrecipitationFactor = 3;
    private static int WindSpeedFactor = 2;
    private static int MinTemp = 30;
    private static int MaxTemp = 90;


    public boolean GoodToRun(int humidity, int temperature, double precipitation, double windSpeed){

        int total = 0;
        if (temperature < MinTemp || temperature > MaxTemp)
            total += 10;
        total += humidity / HumidityFactor;
        total += (precipitation / 2)*PrecipitationFactor;
        total += windSpeed/WindSpeedFactor;
        return total < RunningThreshold;
    }
}
