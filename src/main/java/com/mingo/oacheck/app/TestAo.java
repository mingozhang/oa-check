package com.mingo.oacheck.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mingo.oacheck.domain.Coordinate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author mingo
 * @create 2018-11-23 9:58
 * @desc
 **/
public class TestAo {
    private static final String url = "http://cnc.gisquest.com:89/client.do";
    private static RestTemplate restTemplate = new RestTemplate();
    private static List<Coordinate> coordinates = new ArrayList<>();
    private static String[] address = {
            "浙江省杭州市西湖区华星路70号靠近浙江省电力设计院(华星路)",
            "浙江省杭州市西湖区华星路96号靠近怡泰大厦",
            "浙江省杭州市西湖区华星支路414号靠近东方威尼斯(华星支路)",
            "浙江省杭州市西湖区华星支路76号靠近凯新通信大厦",
            "浙江省杭州市西湖区华星支路921号靠近杭州市西湖区科技局"
    };

    public static void main(String[] args) {
//        coordinates.add(new Coordinate("30.278595,120.120337", "浙江省杭州市西湖区华星路70号靠近浙江省电力设计院(华星路) "));
//        coordinates.add(new Coordinate("30.279429,120.120215", "浙江省杭州市西湖区华星路96号靠近怡泰大厦"));
        testLogin("zhangmy", "zjzs123");

//        int i = (int)(Math.random()*900 + 100);
//        System.out.println(i/4);
    }

    public static void testLogin(String uname, String upassword) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("method", "login");
        params.set("loginid", uname);
        params.set("password", upassword);
        params.set("isneedmoulds", "1");
        params.set("client", "1");
        params.set("clientver", "6.5.45");
        params.set("udid", "868217032042377");
        params.set("clientos", "HUAWEIBKL-AL20");
        params.set("clientosver", "8.0.0");
        params.set("clienttype", "android");
        params.set("language", "zh");
        params.set("country", "CN");
        params.set("relogin", "0");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, params, String.class);
        List<String> cookies = responseEntity.getHeaders().getValuesAsList("Set-Cookie");
        System.out.println(responseEntity);
        JSONObject jsonObject = JSON.parseObject(responseEntity.getBody(), JSONObject.class);
        checkIn(jsonObject.getString("sessionkey"), cookies);
        testLogout(jsonObject.getString("sessionkey"), cookies);
    }

    public static void testLogout(String sessionkey, List<String> cookies) {
        Map<String, String> params = new HashMap<>();
        params.put("method", "logout");
        params.put("sessionkey", sessionkey);
        StringBuilder urlParam = new StringBuilder(url.concat("?"));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String _url = urlParam.substring(0, urlParam.length() - 1);
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(_url, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity);
    }

    public static void checkIn(String sessionkey, List<String> cookies) {
        Map<String, String> params = new HashMap<>();
        params.put("method", "checkin");
        params.put("type", "checkin");
        params.put("latlng", "30.278508,120.121639");
//        params.put("addr", "浙江省杭州市西湖区华星路97-2号靠近创业大厦(华星路)");
        Random random = new Random();
        int index = random.nextInt(address.length);
        params.put("addr", address[index]);
        params.put("sessionkey", sessionkey);
        params.put("wifiMac", "");
        StringBuilder urlParam = new StringBuilder(url.concat("?"));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String _url = urlParam.substring(0, urlParam.length() - 1);
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(_url, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity);
    }
}
