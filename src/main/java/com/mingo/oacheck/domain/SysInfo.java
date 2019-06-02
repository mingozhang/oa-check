package com.mingo.oacheck.domain;

import lombok.Data;

/**
 * @author mingo
 * @create 2019-06-02 16:15
 * @desc
 **/
@Data
public class SysInfo {
    private int bsm;
    private String key;
    private String valule;
    private String bz;


    public SysInfo() {
    }

    public SysInfo(int bsm, String key, String valule, String bz) {
        this.bsm = bsm;
        this.key = key;
        this.valule = valule;
        this.bz = bz;
    }
}
