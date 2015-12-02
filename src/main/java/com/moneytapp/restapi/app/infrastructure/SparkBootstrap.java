/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.app.infrastructure;


import com.moneytapp.restapi.infrastructure.Bootstrap;
import com.moneytapp.restapi.infrastructure.Configuration;
import com.moneytapp.restapi.infrastructure.Controller;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class SparkBootstrap extends Bootstrap {

    public static final int STATUS_CONTROLLER_METHOD_EXECUTION_ERROR = 500;

    public static final String STATUS_CONTROLLER_METHOD_INVOCATION_DESCRIPTION =
            "Required \"%s\" controller method not found";


    private SparkControllerFactory controllerFactory;


    public SparkBootstrap(SparkConfiguration configuration) {
        super(configuration);
        controllerFactory = new SparkControllerFactory(configuration);
    }


    @Override
    public SparkBootstrap setConfiguration(Configuration configuration) {
        super.setConfiguration(configuration);

        return this;
    }


    @Override
    public SparkConfiguration getConfiguration() {
        return (SparkConfiguration) super.getConfiguration();
    }


    public SparkBootstrap run() {
        Spark.ipAddress(getConfiguration().getHost());
        Spark.port(getConfiguration().getPort());

        addRoutes(getConfiguration());

        return this;
    }


    private SparkBootstrap addRoutes(SparkConfiguration configuration) {
        configuration
                .getRoutes()
                .stream()
                .forEach(this::addRoute);

        return this;
    }


    private SparkBootstrap addRoute(SparkRoute route) {
        switch (route.getMethod()) {
            case GET:
                Spark.get(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case POST:
                Spark.post(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case PUT:
                Spark.put(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case HEAD:
                Spark.head(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case DELETE:
                Spark.delete(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case CONNECT:
                Spark.connect(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case PATCH:
                Spark.patch(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
            case TRACE:
                Spark.trace(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()));
                break;
        }
        applyRouteFilters(route);

        return this;
    }


    private SparkBootstrap applyRouteFilters(SparkRoute route) {
        applyBeforeRouteFilters(route);
        applyAfterRouteFilters(route);

        return this;
    }


    private SparkBootstrap applyBeforeRouteFilters(SparkRoute route) {
        route
                .getBeforeFilters()
                .forEach(
                        (filter) -> Spark.before(route.getRoute(),
                                route.getAcceptableContentType(), filter));

        getConfiguration()
                .getGlobalBeforeFilters()
                .forEach(
                        (filter) -> Spark.before(route.getRoute(),
                                route.getAcceptableContentType(), filter));

        return this;
    }


    private SparkBootstrap applyAfterRouteFilters(SparkRoute route) {
        route
                .getAfterFilters()
                .forEach(
                        (filter) -> Spark.before(route.getRoute(),
                                route.getAcceptableContentType(), filter));

        getConfiguration()
                .getGlobalAfterFilters()
                .forEach(
                        (filter) -> Spark.before(route.getRoute(),
                                route.getAcceptableContentType(), filter));

        return this;
    }


    private SparkRouteHandler getHandler(String handler) {
        try {
            return new SparkRouteHandler() {
                @Override
                public Object handle(Request request, Response response) throws Exception {
                    return invokeControllerMethod(handler, request, response);
                }
            };
        } catch (SparkControllerFactoryException e) {
            exit(STATUS_CONTROLLER_METHOD_EXECUTION_ERROR, e.getMessage());
            return new SparkRouteHandler();  // Never will be executed
        }
    }


    private Object invokeControllerMethod(
            String handler,
            Request request,
            Response response
    ) throws SparkControllerFactoryException {
        try {
            String[] parts = handler.split(getConfiguration().getRouterDelimiter());

            String controllerName = parts[0];
            String methodName = parts[1];

            Controller controller = controllerFactory.getController(controllerName);
            Method method = controller.getClass().getMethod(
                    methodName, Request.class, Response.class);

            return method.invoke(controller, request, response);
        } catch (InvocationTargetException|IllegalAccessException|NoSuchMethodException e) {
            throw new SparkControllerFactoryException(
                    STATUS_CONTROLLER_METHOD_INVOCATION_DESCRIPTION, handler);
        }
    }


    public void exit(int status, String message) {
        Spark.halt(status, message);
        Spark.stop();
    }
}
