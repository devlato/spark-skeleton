/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


import com.moneytapp.restapi.infrastructure.Exception;


public class SparkException extends Exception {

    public SparkException(String message) {
        super(message);
    }
}
