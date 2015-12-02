/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


public class SparkControllerFactoryException extends SparkException {

    private String controllerName;


    public SparkControllerFactoryException(String message, String controllerName) {
        super(message);
        this.controllerName = controllerName;
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
