package com.mingo.oacheck.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mingo.oacheck.domain.UserEntity;
import com.mingo.oacheck.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author mingo
 * @create 2018-11-23 13:25
 * @desc
 **/
@Service
public class OaCheckService {

    private final String url = "http://cnc.gisquest.com:89/client.do";
        private final String filepath = "/usr/local/userdata/user.txt";
//    private final String filepath = "D://123.txt";
    private RestTemplate restTemplate = new RestTemplate();
    private List<UserEntity> personList = new ArrayList<>();

    private String sessionkey = "";
    private List<String> cookies = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String[] address = {
            "浙江省杭州市西湖区华星路70号靠近浙江省电力设计院(华星路)",
            "浙江省杭州市西湖区华星路96号靠近怡泰大厦",
            "浙江省杭州市西湖区华星支路414号靠近东方威尼斯(华星支路)",
            "浙江省杭州市西湖区华星支路76号靠近凯新通信大厦",
            "浙江省杭州市西湖区华星支路921号靠近杭州市西湖区科技局",
            "浙江省杭州市西湖区华星路97-2号靠近创业大厦(华星路)"
    };


    @Scheduled(cron = "0 10 8,18 * * ?")
    public void executeTask() throws IOException {
        initUserInfo();
        int poolSize = personList.size() > 16 ? 16 : personList.size();
        ExecutorService executor = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        CompletableFuture[] cfs = personList.stream().map(userEntity -> CompletableFuture.runAsync(
                () -> {
                    try {
                        oaCheckIn(userEntity);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, executor)).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).join();
    }


    public void initUserInfo() throws IOException {
        String result = FileUtils.readFile(this.filepath);
        if (StringUtils.isEmpty(result)) return;
        personList = JSONObject.parseArray(result, UserEntity.class);
    }


    private void oaCheckIn(UserEntity userEntity) throws InterruptedException {
        int i = (int) (Math.random() * 900 + 100) / 4 * 1000;
        System.out.println(i);
        Thread.sleep(i);
        String type = "";
        Date date = new Date();
        int hours = date.getHours();
        if (hours < 12) {
            type = "checkin";
        } else {
            type = "checkout";
        }
        execCheckIn(type, userEntity);
    }


    private void execCheckIn(String type, UserEntity userEntity) {
        System.out.println(type + "login");
        System.out.println(userEntity.getUsername());
            login(userEntity.getUsername(), userEntity.getPassword());
            if (StringUtils.isEmpty(sessionkey) || cookies == null || cookies.isEmpty()) {
                logger.error(String.format("==========%s登录失败=======", userEntity.getUsername()));
            } else {
                checkIn(type);
                logout();
            }
    }


    private void login(String uname, String upassword) {
        cookies = new ArrayList<>();
        sessionkey = "";
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
//        System.out.println(responseEntity);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            cookies = responseEntity.getHeaders().getValuesAsList("Set-Cookie");
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody(), JSONObject.class);
            sessionkey = jsonObject.getString("sessionkey");
            logger.info(String.format("========%s登录成功===========", uname));
            logger.info(String.format("========sessionkey:%s========", sessionkey));
        }
    }

    private void checkIn(String type) {
        Map<String, String> params = new HashMap<>();
        params.put("method", "checkin");
        params.put("type", type);
        params.put("latlng", "30.278508,120.121639");
//        params.put("addr", "浙江省杭州市西湖区华星路97-2号靠近创业大厦(华星路)");
        Random random = new Random();
        int index = random.nextInt(address.length);
        params.put("addr", address[index]);
        params.put("sessionkey", this.sessionkey);
        params.put("wifiMac", "");
        String checkName = "checkout".equals(type) ? "签退" : "签到";
        try {
            JSONObject jsonObject = request(params);
            if ("success".equals(jsonObject.getString("result"))) {
                logger.info(String.format("=======%s成功==========", checkName));
            } else {
                logger.info(String.format("=======%s失败==========", checkName));
            }
            logger.info(jsonObject.getString("msg"));
        } catch (Exception e) {
            logger.error(String.format("=======%s失败==========", checkName));
            logger.error(e.getMessage());
        }

    }


    public void logout() {
        Map<String, String> params = new HashMap<>();
        params.put("method", "logout");
        params.put("sessionkey", sessionkey);
        try {
            JSONObject jsonObject = request(params);
            if (jsonObject.getBoolean("result")) {
                logger.info("============注销成功===============");
            }
        } catch (Exception e) {
            logger.error("==========注销失败=============");
            logger.error(e.getMessage());
        }

    }

    private JSONObject request(Map<String, String> params) {
        StringBuilder urlParam = new StringBuilder(this.url.concat("?"));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String _url = urlParam.substring(0, urlParam.length() - 1);
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, this.cookies);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(_url, HttpMethod.GET, entity, String.class);
//        System.out.println(responseEntity);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return JSONObject.parseObject(responseEntity.getBody(), JSONObject.class);
        }
        return null;
    }


    public void addPerson(String username, String password) throws IOException {
        UserEntity userEntity = new UserEntity(username, password);
        personList.add(userEntity);
        FileUtils.writeTxtFile(JSONObject.toJSONString(personList), this.filepath);
    }


    public List<UserEntity> getPersons() {
        return personList;
    }

    public void deletePerson(String username) throws IOException {
        personList.stream().forEach(item -> {
            if (username.equals(item.getUsername())) {
                personList.remove(item);
            }
        });
        FileUtils.writeTxtFile(JSONObject.toJSONString(personList), this.filepath);
    }
}
