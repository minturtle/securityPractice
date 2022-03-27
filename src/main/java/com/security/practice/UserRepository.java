package com.security.practice;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private static final Map<String, User> userList = new HashMap<>();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRepository() {
        userList.put("kim", new User("kim", passwordEncoder.encode("1111"),
                "minseok", "ADMIN"));
    }

    public User findUserByUsername(String username){

        User user = userList.get(username);
        System.out.println(user);
        return user;
    }
}
