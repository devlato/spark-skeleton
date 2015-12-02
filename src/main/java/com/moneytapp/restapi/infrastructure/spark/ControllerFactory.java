/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


import com.moneytapp.restapi.infrastructure.base.Controller;
import java.util.HashMap;
import java.util.Map;


public class ControllerFactory {
    private Configuration configuration;

    private static Map<String, Controller> controllers = new HashMap<>();


    public ControllerFactory(Configuration configuration) {
        setConfiguration(configuration);
    }


    public ControllerFactory setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }


    public Controller getController(
            String controllerName
    ) throws ControllerFactoryException {
        if (controllers.get(controllerName) == null) {
            controllers.put(controllerName, newController(controllerName));
        }

        return controllers.get(controllerName);
    }


    public Controller newController(
            String controllerName
    ) throws ControllerFactoryException {
        try {
            return (Controller) importController(controllerName).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new ControllerFactoryException(
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


    public Configuration getConfiguration() {
        return configuration;
    }
}
