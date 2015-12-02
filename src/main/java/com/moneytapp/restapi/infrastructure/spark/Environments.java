/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


public enum Environments {
    DEVELOPMENT("development"),
    PRODUCTION("production"),
    TEST("test");


    String environment;


    Environments(String environment) {
        this.environment = environment;
    }


    @Override
    public String toString() {
        return environment;
    }
}
