/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.infrastructure.base;


public abstract class Configuration {

    public static final String DEFAULT_HOST = "localhost";

    public static final int DEFAULT_PORT = 4567;


    private int port = DEFAULT_PORT;

    private String host = DEFAULT_HOST;



    public Configuration() {
        setPort(port);
        setHost(host);
    }


    public int getPort() {
        return port;
    }


    protected Configuration setPort(int port) {
        this.port = port;

        return this;
    }


    public String getHost() {
        return host;
    }


    protected Configuration setHost(String host) {
        this.host = host;

        return this;
    }
}
