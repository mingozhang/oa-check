package com.mingo.oacheck.client;

import com.mingo.oacheck.app.UserService;
import com.mingo.oacheck.vo.ResponseBody;
import com.mingo.oacheck.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mingo
 * @create 2019-06-02 17:12
 * @desc
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{bsm}")
    public ResponseBody<UserVo> get(@PathVariable("bsm") int bsm){
        return userService.get(bsm);
    }

}
