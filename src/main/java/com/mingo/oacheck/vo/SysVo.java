package com.mingo.oacheck.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mingo
 * @create 2019-06-02 17:02
 * @desc
 **/
@Data
public class SysVo implements Serializable {
    private static final long serialVersionUID = 5628959406637171478L;

    private int bsm;
    private String key;
    private String value;
    private String bz;

    public SysVo() {
    }

    public SysVo(int bsm, String key, String value, String bz) {
        this.bsm = bsm;
        this.key = key;
        this.value = value;
        this.bz = bz;
    }
}
