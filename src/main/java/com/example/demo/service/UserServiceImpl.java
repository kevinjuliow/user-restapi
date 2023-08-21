package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo ;
    @Override
    public User saveUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        user.setPassword(hashPassword(user.getPassword()));
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

    private String hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        enc.encodeToString(salt);
        return enc.encodeToString(hash);
    }
}

