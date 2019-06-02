package com.mingo.oacheck.dao;

import com.mingo.oacheck.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mingo
 * @create 2019-06-02 16:18
 * @desc
 **/
@Mapper
public interface UserDao extends CommonDao<UserInfo>{

    UserInfo selectByUsername(@Param("username") String username);

}
