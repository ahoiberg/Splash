package com.example.andrewhoiberg.thirsty_bro;

/**
 * Created by andrewhoiberg on 1/18/15.
 */
public class PronationCalculator{

    private static int THRESHOLD1 = 100;
    private static int THRESHOLD5 = 150;

    public static double CalculateAverage(double average, double mtb1, double mtb5, int sampleSize) {

        if ((mtb1 > THRESHOLD1) && (mtb5 > THRESHOLD5)) {

            double ratio = mtb1 / mtb5;

            return average + ((ratio - average) / (sampleSize + 1));
        }
        return average;

    }

    public enum Pronation {
        OVER, UNDER, NORMAL
    }

    public static Pronation determinePronation(double average){

        if (average < .94)
            return Pronation.UNDER;
        else if (average > .96)
            return Pronation.OVER;
        else return Pronation.NORMAL;

    }
}
