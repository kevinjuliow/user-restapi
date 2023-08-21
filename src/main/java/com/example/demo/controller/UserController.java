package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
@Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Void> createUser (@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
    userService.saveUser(user);
    return new ResponseEntity<Void>(HttpStatus.OK);
}

@GetMapping()
    public List<User> getAllUser (){
    return userService.getAllUsers();
}

@PutMapping("/{id}")
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
@DeleteMapping("/{id}")
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
