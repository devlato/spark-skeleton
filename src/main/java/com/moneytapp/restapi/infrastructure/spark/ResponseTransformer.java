/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


public abstract class ResponseTransformer implements spark.ResponseTransformer {

    @Override
    public abstract String render(Object model) throws Exception;
}
