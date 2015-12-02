/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi;


import com.moneytapp.restapi.infrastructure.spark.Bootstrap;
import com.moneytapp.restapi.infrastructure.spark.ConfigurationFactory;
import com.moneytapp.restapi.infrastructure.spark.Environments;


public class Main {

    public static void main(String[] args) {
        new Bootstrap(ConfigurationFactory.getConfiguration(Environments.DEVELOPMENT)).run();
    }
}
