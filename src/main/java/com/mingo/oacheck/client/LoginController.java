package com.mingo.oacheck.client;

import com.mingo.oacheck.app.OaCheckService;
import com.mingo.oacheck.domain.ResponseBody;
import com.mingo.oacheck.domain.UserEntity;
import com.mingo.oacheck.utils.FileUtils;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author mingo
 * @create 2018-11-29 17:05
 * @desc
 **/
@RestController
public class LoginController {

    @Autowired
    OaCheckService oaCheckService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseBody<String> login(@RequestBody UserEntity userEntity){
        if("mingo".equals(userEntity) && "642591".equals(userEntity.getPassword())){
            return new ResponseBody<>("login success");
        }
        return new ResponseBody<>("login fail");
    }


    @RequestMapping(value = "/readtxt/{os}",method = RequestMethod.GET)
    public String readtxt(@PathVariable String os) throws IOException {
        if("windows".equals(os)) {
            return FileUtils.readFile("D://123.txt");
        }else if("linux".equals(os)){
            return FileUtils.readFile("/usr/local/userdata/user.txt");
        }else {
            return "数据不存在";
        }
    }

    @RequestMapping(value = "/inituser",method = RequestMethod.GET)
    public ResponseBody<List<UserEntity>> initUser() throws IOException {
        oaCheckService.initUserInfo();
        return new ResponseBody<>(oaCheckService.getPersons());
    }


    @RequestMapping(value = "/exec",method = RequestMethod.GET)
    public void execTask() throws IOException {
        oaCheckService.executeTask();
    }


    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String getVersion(){
        return "1.0.1";
    }
}
