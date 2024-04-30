package com.cimba.lazurite.controller;

import com.cimba.lazurite.entity.dto.UserDto;
import com.cimba.lazurite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * Endpoint for registering a new user.
     *
     * @param userDto the user data
     * @return a response entity indicating success or failure
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        try {
            userService.registerUser(userDto);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }catch (Exception e) {  //RegistrationException
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for retrieving all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for retrieving a user by their identifier.
     *
     * @param userId the user identifier
     * @return the user data
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        try {
            final UserDto userDto = userService.getUserById(userId);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (Exception e) {  //UserNotFoundException
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for updating user data.
     *
     * @param userId  the user identifier
     * @param userDto the new user data
     * @return a response entity indicating success or failure
     */
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        try {
            userService.updateUser(userId,userDto);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (Exception e) {     //UserNotFoundException
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for deleting a user.
     *
     * @param userId the user identifier
     * @return a response entity indicating success or failure
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch ( Exception e) {    //UserNotFoundException
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
