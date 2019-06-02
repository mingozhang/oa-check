package com.mingo.oacheck.domain;

import lombok.Data;

/**
 * @author mingo
 * @create 2019-06-02 16:16
 * @desc
 **/
@Data
public class OaUserInfo {

    private int bsm;
    private int oaUserBsm;
    private String oaUsername;
    private String oaPassword;


    public OaUserInfo() {
    }

    public OaUserInfo(int bsm, int oaUserBsm, String oaUsername, String oaPassword) {
        this.bsm = bsm;
        this.oaUserBsm = oaUserBsm;
        this.oaUsername = oaUsername;
        this.oaPassword = oaPassword;
    }
}
