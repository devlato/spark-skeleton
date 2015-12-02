/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


import com.moneytapp.restapi.app.infrastructure.transformers.JsonTransformer;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;


public abstract class Configuration extends com.moneytapp.restapi.infrastructure.base.Configuration {

    private static final int PORT = 8095;

    private static final String HOST = "localhost";

    private String routerDelimiter = ":";

    private String controllerPackage = "com.moneytapp.restapi.app.controllers";

    private String acceptableContentType = "application/json";

    private final List<Route> routes = new ArrayList<>();

    private final List<Filter> beforeFilters = new ArrayList<>();

    private final List<Filter> afterFilters = new ArrayList<>();

    private ResponseTransformer transformer = new JsonTransformer();


    public Configuration() {
        super();
        setPort(PORT);
        setHost(HOST);
        setRoutes();
        setGlobalBeforeFilters();
        setGlobalAfterFilters();
    }


    protected Configuration setRoutes() {
        return this;
    }


    protected Configuration setGlobalBeforeFilters() {
        return this;
    }


    protected Configuration setGlobalAfterFilters() {
        return this;
    }


    protected Configuration addRoute(Route route) {
        routes.add(route);

        return this;
    }


    protected Configuration addGlobalBeforeFilter(Filter filter) {
        beforeFilters.add(filter);

        return this;
    }


    protected Configuration addGlobalBeforeFilter(BiConsumer<Request, Response> filter) {
        addGlobalBeforeFilter(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                filter.accept(request, response);
            }
        });

        return this;
    }


    protected Configuration addGlobalAfterFilter(Filter filter) {
        afterFilters.add(filter);

        return this;
    }


    protected Configuration addGlobalAfterFilter(BiConsumer<Request, Response> filter) {
        addGlobalAfterFilter(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                filter.accept(request, response);
            }
        });

        return this;
    }


    protected Configuration setGlobalTransformer(ResponseTransformer transformer) {
        this.transformer = transformer;

        return this;
    }


    protected Configuration setRouterDelimiter(String routerDelimiter) {
        this.routerDelimiter = routerDelimiter;

        return this;
    }


    protected Configuration setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;

        return this;
    }


    protected Configuration setAcceptableContentType(String acceptableContentType) {
        this.acceptableContentType = acceptableContentType;

        return this;
    }


    public List<Route> getRoutes() {
        return routes;
    }


    public List<Filter> getGlobalBeforeFilters() {
        return beforeFilters;
    }


    public List<Filter> getGlobalAfterFilters() {
        return afterFilters;
    }


    public ResponseTransformer getGlobalTransformer() {
        return transformer;
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
