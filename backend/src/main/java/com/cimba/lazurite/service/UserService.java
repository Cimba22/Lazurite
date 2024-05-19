package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.Role;
import com.cimba.lazurite.entity.dto.UserDto;
import com.cimba.lazurite.exception.RegistrationException;
import com.cimba.lazurite.exception.UserNotFoundException;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {

    Role getUserRole(String username);

    /**
     * Registers a new user.
     *
     * @param userDto the user data
     * @throws RegistrationException if an error occurs during registration
     */
    void registerUser(UserDto userDto) throws RegistrationException;

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    List<UserDto> getAllUsers();

    /**
     * Retrieves a user by their identifier.
     *
     * @param userId the user identifier
     * @return the user data
     */
    UserDto getUserById(Long userId);

    /**
     * Updates user data.
     *
     * @param userId  the user identifier
     * @param userDto the new user data
     * @throws UserNotFoundException if the user with the specified identifier is not found
     */
    void updateUser(Long userId, UserDto userDto) throws UserNotFoundException;

    /**
     * Deletes a user.
     *
     * @param userId the user identifier
     * @throws UserNotFoundException if the user with the specified identifier is not found
     */
    void deleteUser(Long userId) throws UserNotFoundException;

}

