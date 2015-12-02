/**
 * Created by d.tokarev on 01/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


import com.moneytapp.restapi.infrastructure.base.Controller;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Bootstrap extends com.moneytapp.restapi.infrastructure.base.Bootstrap {

    public static final int STATUS_CONTROLLER_METHOD_EXECUTION_ERROR = 500;

    public static final String STATUS_CONTROLLER_METHOD_INVOCATION_DESCRIPTION =
            "Required \"%s\" controller method not found";


    private ControllerFactory controllerFactory;


    public Bootstrap(Configuration configuration) {
        super(configuration);
        controllerFactory = new ControllerFactory(configuration);
    }


    @Override
    public Bootstrap setConfiguration(com.moneytapp.restapi.infrastructure.base.Configuration configuration) {
        super.setConfiguration(configuration);

        return this;
    }


    @Override
    public Configuration getConfiguration() {
        return (Configuration) super.getConfiguration();
    }


    public Bootstrap run() {
        Spark.ipAddress(getConfiguration().getHost());
        Spark.port(getConfiguration().getPort());

        addRoutes(getConfiguration());

        return this;
    }


    public Bootstrap stop() {
        Spark.stop();

        return this;
    }


    public Bootstrap exit(int status, String message) {
        Spark.halt(status, message);
        stop();

        return this;
    }


    private Bootstrap addRoutes(Configuration configuration) {
        configuration
                .getRoutes()
                .stream()
                .forEach(this::addRoute);

        return this;
    }


    private Route addRoute(Route route) {
        applyRouteTransformers(route);
        switch (route.getMethod()) {
            case GET:
                Spark.get(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case POST:
                Spark.post(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case PUT:
                Spark.put(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case HEAD:
                Spark.head(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case DELETE:
                Spark.delete(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case CONNECT:
                Spark.connect(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case PATCH:
                Spark.patch(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
            case TRACE:
                Spark.trace(route.getRoute(), route.getAcceptableContentType(),
                        getHandler(route.getHandler()), route.getTransformer());
                break;
        }
        applyRouteFilters(route);

        return route;
    }


    private Route applyRouteFilters(Route route) {
        applyBeforeRouteFilters(route);
        applyAfterRouteFilters(route);

        return route;
    }


    private Route applyRouteTransformers(Route route) {
        if (route.getTransformer() == null) {
            route.setTransformer(getConfiguration().getGlobalTransformer());
        }

        return route;
    }


    private Route applyBeforeRouteFilters(Route route) {
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

        return route;
    }


    private Route applyAfterRouteFilters(Route route) {
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

        return route;
    }


    private RouteHandler getHandler(String handler) {
        try {
            return new RouteHandler() {
                @Override
                public Object handle(Request request, Response response) throws com.moneytapp.restapi.infrastructure.spark.Exception {
                    return invokeControllerMethod(handler, request, response);
                }
            };
        } catch (ControllerFactoryException e) {
            exit(STATUS_CONTROLLER_METHOD_EXECUTION_ERROR, e.getMessage());
            return new RouteHandler();  // Never will be executed
        }
    }


    private Object invokeControllerMethod(
            String handler,
            Request request,
            Response response
    ) throws ControllerFactoryException {
        try {
            String[] parts = handler.split(getConfiguration().getRouterDelimiter());

            String controllerName = parts[0];
            String methodName = parts[1];

            Controller controller = controllerFactory.getController(controllerName);
            Method method = controller.getClass().getMethod(
                    methodName, Request.class, Response.class);

            return method.invoke(controller, request, response);
        } catch (InvocationTargetException|IllegalAccessException|NoSuchMethodException e) {
            throw new ControllerFactoryException(
                    STATUS_CONTROLLER_METHOD_INVOCATION_DESCRIPTION, handler);
        }
    }
}
