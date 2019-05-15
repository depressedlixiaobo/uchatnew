package com.lux.uchat.dao;

import com.lux.uchat.domain.FriendRequst;

public interface FriendRequstMapper {
    int deleteByPrimaryKey(String id);

    int insert(FriendRequst record);

    int insertSelective(FriendRequst record);

    FriendRequst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FriendRequst record);

    int updateByPrimaryKey(FriendRequst record);
}