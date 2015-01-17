package com.example.andrewhoiberg.thirsty_bro;

import com.sensoria.sensorialibrary.SAAnklet;

import java.io.Serializable;

/**
 * Created by Martin on 1/17/2015.
 */
public class AnkletPasser implements Serializable {
    SAAnklet anklet;
    public AnkletPasser(SAAnklet anklet){
        this.anklet = anklet;
    }
}
