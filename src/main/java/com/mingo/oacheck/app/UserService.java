package com.mingo.oacheck.app;

import com.mingo.oacheck.domain.UserInfo;
import com.mingo.oacheck.vo.ResponseBody;
import com.mingo.oacheck.vo.UserVo;

import java.util.List;

/**
 * @author mingo
 * @create 2019-06-02 16:58
 * @desc
 **/
public interface UserService {

    ResponseBody<UserVo> get(int bsm);

    ResponseBody<List<UserVo>> getList();

    ResponseBody<UserVo> get(String username);

    ResponseBody<String> save(UserVo userInfo);

    ResponseBody<String> update(UserVo userInfo);

    ResponseBody<String> delete(int bsm);
}
