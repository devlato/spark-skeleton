/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


public class ControllerFactoryException extends Exception {

    private String controllerName;


    public ControllerFactoryException(String message, String controllerName) {
        super(message);
        setControllerName(controllerName);
    }


    public String getControllerName() {
        return controllerName;
    }


    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }


    @Override
    public String getMessage() {
        return String.format(super.getMessage(), getControllerName());
    }
}
