/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


import com.moneytapp.restapi.infrastructure.Configuration;
import java.util.ArrayList;
import java.util.List;


public class SparkConfiguration extends Configuration {

    public SparkConfiguration() {
        setPort(8095);
        setHost("localhost");
    }


    public List<SparkRoute> getRoutes() {
        List<SparkRoute> routes = new ArrayList<>();

        routes.add(new SparkRoute("/", SparkHttpMethods.GET, "DefaultController:index"));

        return routes;
    }


    public String getRouterDelimiter() {
        return ":";
    }


    public String getControllerPackage() {
        return "com.moneytapp.restapi.app.controllers";
    }
}
