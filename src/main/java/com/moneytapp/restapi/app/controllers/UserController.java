/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.controllers;


import com.moneytapp.restapi.app.models.User;
import com.moneytapp.restapi.app.models.UserId;
import com.moneytapp.restapi.app.services.UserService;
import com.moneytapp.restapi.infrastructure.spark.Controller;
import spark.Request;
import spark.Response;


public class UserController extends Controller {

    private UserService userService;


    public UserController() {
        userService = new UserService();
    }


    public User getUser(Request request, Response response) {
        String userIdString = request.params("userId");
        UserId userId = UserId.valueOf(userIdString);

        return userService.findOne(userId);
    }
}
