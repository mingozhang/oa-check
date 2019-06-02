package com.mingo.oacheck.dao;

import com.mingo.oacheck.domain.OaUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author mingo
 * @create 2019-06-02 16:39
 * @desc
 **/
@Mapper
public interface OaUserDao extends CommonDao<OaUserInfo> {

    OaUserInfo selectByUserBsm(@Param("userBsm") int userBsm);
}
