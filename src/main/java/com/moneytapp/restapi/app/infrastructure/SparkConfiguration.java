/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


import com.moneytapp.restapi.infrastructure.Configuration;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;


public class SparkConfiguration extends Configuration {

    private static final int PORT = 8095;

    private static final String HOST = "localhost";

    private static final String routerDelimiter = ":";

    private static final String controllerPackage = "com.moneytapp.restapi.app.controllers";

    private static final String acceptableContentType = "application/json";

    private static final List<SparkRoute> routes = new ArrayList<>();

    private static final List<SparkFilter> beforeFilters = new ArrayList<>();

    private static final List<SparkFilter> afterFilters = new ArrayList<>();


    public SparkConfiguration() {
        setPort(PORT);
        setHost(HOST);
        setRoutes();
        setGlobalBeforeFilters();
        setGlobalAfterFilters();
    }


    private SparkConfiguration setRoutes() {
        routes.add(
                new SparkRoute("/", SparkHttpMethods.GET, "DefaultController:index",
                        getAcceptableContentType()));

        return this;
    }


    private SparkConfiguration setGlobalBeforeFilters() {
        beforeFilters.add(new SparkFilter() {
            @Override
            public void handle(Request request, Response response) {
                System.out.println(request.uri() + "" + request.userAgent());
            }
        });

        return this;
    }


    private SparkConfiguration setGlobalAfterFilters() {
        afterFilters.add(new SparkFilter() {
            @Override
            public void handle(Request request, Response response) {
                response.header("X-Powered-By", "Spark");
            }
        });

        return this;
    }


    public List<SparkRoute> getRoutes() {
        return routes;
    }


    public List<SparkFilter> getGlobalBeforeFilters() {
        return beforeFilters;
    }


    public List<SparkFilter> getGlobalAfterFilters() {
        return afterFilters;
    }


    public String getAcceptableContentType() {
        return acceptableContentType;
    }


    public String getRouterDelimiter() {
        return routerDelimiter;
    }


    public String getControllerPackage() {
        return controllerPackage;
    }
}
