package com.mingo.oacheck.domain;

import java.io.Serializable;

/**
 * @author mingo
 * @create 2018-11-29 17:13
 * @desc
 **/
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 9128344145774297337L;

    private String username;

    private String password;

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

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
