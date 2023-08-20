package com.example.demo.service;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    public User saveUser (User user) ;
    public List<User> getAllUsers ();

    public User updateUser (int id , User user );

    public void deleteUser (int id );
}
