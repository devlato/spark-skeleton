/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


import spark.Request;
import spark.Response;


public abstract class Filter implements spark.Filter {

    @Override
    public abstract void handle(Request request, Response response);
}
