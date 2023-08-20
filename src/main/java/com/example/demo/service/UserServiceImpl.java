package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo ;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(int id, User user) {
        User existUser = userRepo.findById(id).orElseThrow(()-> new NoSuchElementException());
        existUser.setName(user.getName());
        existUser.setAge(user.getAge());
        existUser.setJob(user.getJob());

        return userRepo.save(existUser);
    }

    @Override
    public void deleteUser(int id) {
        User existUser = userRepo.findById(id).orElseThrow(()-> new NoSuchElementException());
        userRepo.delete(existUser);
    }
}
