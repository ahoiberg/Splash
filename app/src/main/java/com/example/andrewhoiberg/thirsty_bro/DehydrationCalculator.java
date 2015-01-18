package com.example.andrewhoiberg.thirsty_bro;

/**
 * Created by andrewhoiberg on 1/18/15.
 */
public class DehydrationCalculator {

    final static double StepFactor = .6;
    final static double WeightFactor = .2;
    final static double TemperatureFactor = .5;
    final static double FemaleGenderFactor = 1.01;
    final static double MaleGenderFactor = 1.02;
    final static double MaxHourlyIntake = 1800;

    public enum Gender {
        MALE, FEMALE
    }

    /**
     *
     * @param numSteps
     * @param weight in pounds
     * @param temp in Fahrenheit
     * @param timeElapsed in minutes
     * @param g
     * @return recommended intake in milliliters
     * this class is called every time data is collected over the complete set of data
     */
    public static double CalculateRecommendedLiters(int numSteps, int weight, int temp, int timeElapsed, Gender g) {

        double genderFactor;
        double recommendedIntake;
        genderFactor = g==Gender.MALE? MaleGenderFactor:FemaleGenderFactor;


        double intensity = numSteps/timeElapsed;

        recommendedIntake = Math.min(numSteps*StepFactor + weight*WeightFactor + temp*TemperatureFactor + intensity,MaxHourlyIntake*timeElapsed/60)*genderFactor;

        return recommendedIntake;

    }
}
