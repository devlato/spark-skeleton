/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


import spark.Filter;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class SparkRoute {

    private String route;

    private SparkHttpMethods httpMethod;

    private String handler;

    private String acceptableContentType;

    private List<Filter> beforeFilters = new ArrayList<>();

    private List<Filter> afterFilters = new ArrayList<>();

    private SparkTransformer transformer;


    public SparkRoute(String route, SparkHttpMethods method, String handler) {
        this(route, method, handler, null);
    }


    public SparkRoute(String route, SparkHttpMethods method, String handler, String acceptableContentType) {
        setRoute(route);
        setMethod(method);
        setHandler(handler);
        setAcceptableContentType(acceptableContentType);
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


    public String getAcceptableContentType() {
        return acceptableContentType;
    }


    public SparkRoute setAcceptableContentType(String acceptableContentType) {
        this.acceptableContentType = acceptableContentType;

        return this;
    }


    public List<Filter> getBeforeFilters() {
        return beforeFilters;
    }


    public SparkRoute addBeforeFilter(Filter beforeFilter) {
        beforeFilters.add(beforeFilter);

        return this;
    }


    public SparkRoute addBeforeFilter(BiConsumer<Request, Response> beforeFilter) {
        beforeFilters.add(new SparkFilter() {
            @Override
            public void handle(Request request, Response response) {
                beforeFilter.accept(request, response);
            }
        });

        return this;
    }


    public SparkRoute addBeforeFilters(SparkFilter... beforeFilters) {
        addBeforeFilters(Stream.of(beforeFilters).collect(toList()));

        return this;
    }


    public SparkRoute addBeforeFilters(List<SparkFilter> beforeFilters) {
        this.beforeFilters.addAll(beforeFilters);

        return this;
    }


    public List<Filter> getAfterFilters() {
        return afterFilters;
    }


    public SparkRoute addAfterFilter(Filter afterFilter) {
        afterFilters.add(afterFilter);

        return this;
    }


    public SparkRoute addAfterFilter(BiConsumer<Request, Response> afterFilter) {
        afterFilters.add(new SparkFilter() {
            @Override
            public void handle(Request request, Response response) {
                afterFilter.accept(request, response);
            }
        });

        return this;
    }


    public SparkRoute addAfterFilters(SparkFilter... afterFilters) {
        addAfterFilters(Stream.of(afterFilters).collect(toList()));

        return this;
    }


    public SparkRoute addAfterFilters(List<SparkFilter> afterFilters) {
        this.afterFilters.addAll(afterFilters);

        return this;
    }


    public SparkTransformer getTransformer() {
        return transformer;
    }


    public SparkRoute setTransformer(SparkTransformer transformer) {
        this.transformer = transformer;

        return this;
    }
}
