/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


public class ConfigurationFactoryException extends Exception {

    private Environments environment;


    public ConfigurationFactoryException(String message, Environments environment) {
        super(message);
        setEnvironment(environment);
    }


    public Environments getEnvironment() {
        return environment;
    }


    public void setEnvironment(Environments environment) {
        this.environment = environment;
    }


    @Override
    public String getMessage() {
        return String.format(super.getMessage(), getEnvironment().name());
    }
}
