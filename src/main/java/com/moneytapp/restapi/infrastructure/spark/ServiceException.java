/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }
}
