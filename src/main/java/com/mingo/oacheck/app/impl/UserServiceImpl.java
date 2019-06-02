package com.mingo.oacheck.app.impl;

import com.mingo.oacheck.app.UserService;
import com.mingo.oacheck.dao.UserDao;
import com.mingo.oacheck.domain.UserInfo;
import com.mingo.oacheck.vo.ResponseBody;
import com.mingo.oacheck.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author mingo
 * @create 2019-06-02 16:57
 * @desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public ResponseBody<UserVo> get(int bsm) {
        UserInfo info = userDao.select(bsm);
        UserVo vo = new UserVo(info.getBsm(),info.getUsername(),info.getPassword(),
                info.getEmail(),sdf.format(info.getLastLoginTime()));
        return new ResponseBody<>(vo);
    }

    @Override
    public ResponseBody<List<UserVo>> getList() {
        return null;
    }

    @Override
    public ResponseBody<UserVo> get(String username) {
        return null;
    }

    @Override
    public ResponseBody<String> save(UserVo userInfo) {
        return null;
    }

    @Override
    public ResponseBody<String> update(UserVo userInfo) {
        return null;
    }

    @Override
    public ResponseBody<String> delete(int bsm) {
        return null;
    }
}
