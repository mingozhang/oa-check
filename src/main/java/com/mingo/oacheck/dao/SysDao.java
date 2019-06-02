package com.mingo.oacheck.dao;

import com.mingo.oacheck.domain.SysInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author mingo
 * @create 2019-06-02 16:34
 * @desc
 **/
@Mapper
public interface SysDao extends CommonDao<SysInfo> {

    SysInfo selectByKey(@Param("key") String key);
}
