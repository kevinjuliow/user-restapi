package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
    private UserService userService;

@PostMapping("/add")
    public String createUser (@RequestBody User user){
    userService.saveUser(user);
    return "New User Created";
}

@GetMapping()
    public List<User> getAllUser (){
    return userService.getAllUsers();
}

@PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser (@PathVariable int id, @RequestBody User user){
    try {
        userService.updateUser(id , user);
        return ResponseEntity.ok("Success Update User Id " + id);
    }catch (NoSuchElementException err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "User With Id "+id+" Not Found in Database"
        );
    }
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable int id){
    try {
        userService.deleteUser(id);
        return ResponseEntity.ok("Succcess Delete User id "+id);
    }catch (NoSuchElementException err){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "User With Id "+id+" Not Found in Database"
        );
    }
}

}
