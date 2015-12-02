/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.app.models;


import com.moneytapp.restapi.infrastructure.spark.Model;


public class User extends Model {

    private String username;


    public User(String username) {
        setUsername(username);
    }


    public User setUsername(String username) {
        this.username = username;

        return this;
    }


    public String getUsername() {
        return username;
    }
}
