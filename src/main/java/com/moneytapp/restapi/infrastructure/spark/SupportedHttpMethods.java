/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


public enum SupportedHttpMethods {
    GET("get"),
    POST("post"),
    DELETE("delete"),
    HEAD("head"),
    PUT("put"),
    CONNECT("connect"),
    PATCH("patch"),
    TRACE("trace");


    String method;


    SupportedHttpMethods(String method) {
        this.method = method;
    }


    @Override
    public String toString() {
        return method;
    }
}
