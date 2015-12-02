/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.services;


import com.moneytapp.restapi.app.models.User;
import com.moneytapp.restapi.app.models.UserId;
import com.moneytapp.restapi.infrastructure.spark.Service;


public class UserService extends Service {
    public UserService() {

    }


    public User findOne(UserId userId) {
        return new User("User that has been found");
    }
}
