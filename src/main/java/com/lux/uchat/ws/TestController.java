package com.lux.uchat.ws;

import com.lux.uchat.domain.Users;
import com.lux.uchat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    UsersService _usersService;
    @RequestMapping("/users")
    public Users Test(){
        return  _usersService.getUser("5f1e0770-88bd-40e2-80da-2bfac78b84a4");
    }
}
