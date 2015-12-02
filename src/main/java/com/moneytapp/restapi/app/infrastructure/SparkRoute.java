/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


public class SparkRoute {

    private String route;

    private SparkHttpMethods httpMethod;

    private String handler;



    public SparkRoute(String route, SparkHttpMethods method, String handler) {
        setRoute(route);
        setMethod(method);
        setHandler(handler);
    }


    public SparkRoute setRoute(String route) {
        this.route = route;

        return this;
    }


    public String getRoute() {
        return route;
    }


    public SparkHttpMethods getMethod() {
        return httpMethod;
    }


    public SparkRoute setMethod(SparkHttpMethods httpMethod) {
        this.httpMethod = httpMethod;

        return this;
    }


    public String getHandler() {
        return handler;
    }


    public SparkRoute setHandler(String handler) {
        this.handler = handler;

        return this;
    }
}
