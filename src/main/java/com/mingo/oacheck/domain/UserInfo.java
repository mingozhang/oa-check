package com.mingo.oacheck.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author mingo
 * @create 2019-06-02 16:04
 * @desc
 **/
@Data
public class UserInfo {

    private int bsm;
    private String username;
    private String password;
    private String email;
    private Date lastLoginTime;


    public UserInfo() {
    }

    public UserInfo(int bsm, String username, String password, String email, Date lastLoginTime) {
        this.bsm = bsm;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLoginTime = lastLoginTime;
    }
}
