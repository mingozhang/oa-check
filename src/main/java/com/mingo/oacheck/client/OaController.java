package com.mingo.oacheck.client;

import com.mingo.oacheck.app.OaCheckService;
import com.mingo.oacheck.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author mingo
 * @create 2018-11-26 17:55
 * @desc
 **/
@RestController
@RequestMapping("/oa")
public class OaController {

    @Autowired
    OaCheckService oaCheckService;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPerson(@RequestParam("username") String username,
                            @RequestParam("password") String password) throws IOException {
        oaCheckService.addPerson(username,password);
        return "ok";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public List<UserEntity> getPersons(){
        return oaCheckService.getPersons();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePerson(@RequestParam("username") String username) throws IOException {
        oaCheckService.deletePerson(username);
        return "ok";
    }
}
