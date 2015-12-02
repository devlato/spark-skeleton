/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.controllers;


import com.moneytapp.restapi.infrastructure.spark.Controller;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DefaultController extends Controller {

    public String index(Request request, Response response) {
        return "Hello, bro!";
    }


    public Map<String, Object> time(Request request, Response response) {
        Map<String, Object> result = new HashMap<>();

        result.put("time", new Date().getTime());

        return result;
    }
}
