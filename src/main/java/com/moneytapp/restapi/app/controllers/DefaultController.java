/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.controllers;


import com.moneytapp.restapi.app.infrastructure.SparkController;
import spark.Request;
import spark.Response;


public class DefaultController extends SparkController {

    public String index(Request request, Response response) {
        return "Hello, bro!";
    }
}
