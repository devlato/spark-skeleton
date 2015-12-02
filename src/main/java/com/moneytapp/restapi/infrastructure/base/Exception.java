/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.base;


public abstract class Exception extends RuntimeException {

    public Exception(String message) {
        super(message);
    }
}
