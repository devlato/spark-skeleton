/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


public enum SparkHttpMethods {
    GET("get"),
    POST("post"),
    DELETE("delete"),
    HEAD("head"),
    PUT("put"),
    CONNECT("connect"),
    PATCH("patch"),
    TRACE("trace");


    String method;


    SparkHttpMethods(String method) {
        this.method = method;
    }


    @Override
    public String toString() {
        return method;
    }
}
