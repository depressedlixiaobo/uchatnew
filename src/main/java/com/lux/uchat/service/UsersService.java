package com.lux.uchat.service;

import com.lux.uchat.dao.UsersMapper;
import com.lux.uchat.domain.Users;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {

    private UsersMapper _userMapper;

    public int registryUser(Users user){
       return  _userMapper.insert(user);
    }

    public Users getUser(String userId){
        System.out.println(_userMapper);
        return  _userMapper.selectByPrimaryKey(userId);
    }
}
