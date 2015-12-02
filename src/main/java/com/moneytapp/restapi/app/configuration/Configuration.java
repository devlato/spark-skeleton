/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.configuration;


import com.moneytapp.restapi.infrastructure.spark.Route;
import com.moneytapp.restapi.infrastructure.spark.SupportedHttpMethods;


public class Configuration extends com.moneytapp.restapi.infrastructure.spark.Configuration {

    @Override
    protected Configuration setRoutes() {
        super.setRoutes();

        addRoute(new Route("/", SupportedHttpMethods.GET, "DefaultController:index",
                getAcceptableContentType()));

        // User routes
        addRoute(new Route("/users/:userId", SupportedHttpMethods.GET, "UserController:getUser",
                getAcceptableContentType()));

        return this;
    }


    @Override
    protected Configuration setGlobalBeforeFilters() {
        super.setGlobalBeforeFilters();
        addGlobalBeforeFilter(
                (request, response) ->
                        System.out.println(request.requestMethod() + " " +
                                request.uri() + " by " + request.userAgent()));

        return this;
    }


    @Override
    protected Configuration setGlobalAfterFilters() {
        super.setGlobalAfterFilters();
        addGlobalAfterFilter((request, response) -> response.header("X-Powered-By", "Spark"));

        return this;
    }
}
