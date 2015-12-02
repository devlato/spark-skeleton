/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.configuration;


import com.moneytapp.restapi.infrastructure.spark.Route;
import com.moneytapp.restapi.infrastructure.spark.SupportedHttpMethods;


public class DevelopmentConfiguration extends Configuration {

    @Override
    protected DevelopmentConfiguration setRoutes() {
        super.setRoutes();
        addRoute(new Route("/time", SupportedHttpMethods.GET, "DefaultController:time",
                getAcceptableContentType()));

        return this;
    }
}
