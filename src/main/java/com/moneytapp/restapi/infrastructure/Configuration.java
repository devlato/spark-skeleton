/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.infrastructure;


public abstract class Configuration {

    public static final String DEFAULT_HOST = "localhost";

    public static final int DEFAULT_PORT = 4567;


    private int port = DEFAULT_PORT;

    private String host = DEFAULT_HOST;



    public int getPort() {
        return port;
    }


    public Configuration setPort(int port) {
        this.port = port;
        return this;
    }


    public String getHost() {
        return host;
    }


    public Configuration setHost(String host) {
        this.host = host;
        return this;
    }
}
