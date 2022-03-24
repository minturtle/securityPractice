package com.security;

import com.security.practice.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyUserRepository {
    private static final Map<String, User> users = new ConcurrentHashMap<>();

    public User findById(String id){
        return users.get(id);
    }

    public void save(User user){
        users.put(user.getId(), user);
    }
}
