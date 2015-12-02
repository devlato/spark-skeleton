/**
 * Created by d.tokarev on 01/12/15.
 */



package com.moneytapp.restapi.infrastructure;


import spark.Spark;


public abstract class Bootstrap {

    private Configuration configuration;


    public Bootstrap(Configuration configuration) {
        setConfiguration(configuration);
    }


    public Configuration getConfiguration() {
        return configuration;
    }


    public Bootstrap setConfiguration(Configuration configuration) {
        this.configuration = configuration;

        return this;
    }
}
