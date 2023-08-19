package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo ;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }
}
