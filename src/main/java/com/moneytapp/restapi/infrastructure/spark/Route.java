/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class Route {

    private String route;

    private SupportedHttpMethods httpMethod;

    private String handler;

    private String acceptableContentType;

    private List<spark.Filter> beforeFilters = new ArrayList<>();

    private List<spark.Filter> afterFilters = new ArrayList<>();

    private ResponseTransformer transformer;


    public Route(String route, SupportedHttpMethods method, String handler) {
        this(route, method, handler, null);
    }


    public Route(String route, SupportedHttpMethods method, String handler, String acceptableContentType) {
        setRoute(route);
        setMethod(method);
        setHandler(handler);
        setAcceptableContentType(acceptableContentType);
    }


    public Route setRoute(String route) {
        this.route = route;

        return this;
    }


    public String getRoute() {
        return route;
    }


    public SupportedHttpMethods getMethod() {
        return httpMethod;
    }


    public Route setMethod(SupportedHttpMethods httpMethod) {
        this.httpMethod = httpMethod;

        return this;
    }


    public String getHandler() {
        return handler;
    }


    public Route setHandler(String handler) {
        this.handler = handler;

        return this;
    }


    public String getAcceptableContentType() {
        return acceptableContentType;
    }


    public Route setAcceptableContentType(String acceptableContentType) {
        this.acceptableContentType = acceptableContentType;

        return this;
    }


    public List<spark.Filter> getBeforeFilters() {
        return beforeFilters;
    }


    public Route addBeforeFilter(spark.Filter beforeFilter) {
        beforeFilters.add(beforeFilter);

        return this;
    }


    public Route addBeforeFilter(BiConsumer<Request, Response> beforeFilter) {
        beforeFilters.add(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                beforeFilter.accept(request, response);
            }
        });

        return this;
    }


    public Route addBeforeFilters(Filter... beforeFilters) {
        addBeforeFilters(Stream.of(beforeFilters).collect(toList()));

        return this;
    }


    public Route addBeforeFilters(List<Filter> beforeFilters) {
        this.beforeFilters.addAll(beforeFilters);

        return this;
    }


    public List<spark.Filter> getAfterFilters() {
        return afterFilters;
    }


    public Route addAfterFilter(spark.Filter afterFilter) {
        afterFilters.add(afterFilter);

        return this;
    }


    public Route addAfterFilter(BiConsumer<Request, Response> afterFilter) {
        afterFilters.add(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                afterFilter.accept(request, response);
            }
        });

        return this;
    }


    public Route addAfterFilters(Filter... afterFilters) {
        addAfterFilters(Stream.of(afterFilters).collect(toList()));

        return this;
    }


    public Route addAfterFilters(List<Filter> afterFilters) {
        this.afterFilters.addAll(afterFilters);

        return this;
    }


    public ResponseTransformer getTransformer() {
        return transformer;
    }


    public Route setTransformer(ResponseTransformer transformer) {
        this.transformer = transformer;

        return this;
    }
}
