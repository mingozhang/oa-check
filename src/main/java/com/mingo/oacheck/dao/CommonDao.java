package com.mingo.oacheck.dao;

import com.mingo.oacheck.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mingo
 * @create 2019-06-02 16:35
 * @desc
 **/
public interface CommonDao<E> {

    List<E> selectList();

    E select(@Param("bsm") int bsm);

    int insert(E e);

    int update(E e);

    int delete(@Param("bsm") int bsm);
}
