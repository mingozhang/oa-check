package com.mingo.oacheck.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mingo
 * @create 2019-06-02 17:01
 * @desc
 **/
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -6795352345003346250L;

    private int bsm;
    private String username;
    private String password;
    private String email;
    private String lastLoginTime;

    public UserVo() {
    }

    public UserVo(int bsm, String username, String password, String email, String lastLoginTime) {
        this.bsm = bsm;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLoginTime = lastLoginTime;
    }
}
