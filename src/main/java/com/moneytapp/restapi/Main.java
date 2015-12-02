/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi;


import com.moneytapp.restapi.app.infrastructure.SparkBootstrap;
import com.moneytapp.restapi.app.infrastructure.SparkConfiguration;


public class Main {

    public static void main(String[] args) {
        new SparkBootstrap(new SparkConfiguration()).run();
    }
}
