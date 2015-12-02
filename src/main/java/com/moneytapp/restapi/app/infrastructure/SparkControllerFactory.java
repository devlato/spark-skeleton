/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


import com.moneytapp.restapi.infrastructure.Controller;

import java.util.HashMap;
import java.util.Map;


public class SparkControllerFactory {
    private SparkConfiguration configuration;

    private static Map<String, Controller> controllers = new HashMap<>();


    public SparkControllerFactory(SparkConfiguration configuration) {
        setConfiguration(configuration);
    }


    public SparkControllerFactory setConfiguration(SparkConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }


    public Controller getController(
            String controllerName
    ) throws SparkControllerFactoryException {
        if (controllers.get(controllerName) == null) {
            controllers.put(controllerName, newController(controllerName));
        }

        return controllers.get(controllerName);
    }


    public Controller newController(
            String controllerName
    ) throws SparkControllerFactoryException {
        try {
            return (Controller) importController(controllerName).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new SparkControllerFactoryException(
                    "Cannot get %s controller instance", controllerName);
        }
    }


    public Class importController(
            String controllerName
    ) throws ClassNotFoundException {
        return Class.forName(getControllerFullQualifiedName(controllerName));
    }


    public String getControllerPackage() {
        return getConfiguration().getControllerPackage();
    }


    public String getControllerFullQualifiedName(String controllerName) {
        return getControllerPackage() + "." + controllerName;
    }


    public SparkConfiguration getConfiguration() {
        return configuration;
    }
}
