package com.mingo.oacheck.domain;

/**
 * @author mingo
 * @create 2018-12-07 18:21
 * @desc
 **/
public class Coordinate {
    //经纬度
    private String latlng;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coordinate() {
    }

    public Coordinate(String latlng, String address) {
        this.latlng = latlng;
        this.address = address;
    }
}
