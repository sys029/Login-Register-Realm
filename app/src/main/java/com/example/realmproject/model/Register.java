package com.example.realmproject.model;

import io.realm.RealmObject;

public class Register extends RealmObject {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Register{" +
                "username= " + username + " " +
                ", password= " + password + " }" +
                "\n";
    }
}
