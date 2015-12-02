/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.infrastructure.transformers;


import com.google.gson.Gson;
import com.moneytapp.restapi.infrastructure.spark.ResponseTransformer;


public class JsonTransformer extends ResponseTransformer {
    private static final Gson gson;


    static {
        gson = new Gson();
    }


    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
