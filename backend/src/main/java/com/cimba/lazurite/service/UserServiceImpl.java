package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.dto.UserDto;
import com.cimba.lazurite.exception.RegistrationException;
import com.cimba.lazurite.exception.UserNotFoundException;
import com.cimba.lazurite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserDto userDto) throws RegistrationException {

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User with id " + idUser + " not found"));
        return mapToDto(user);
    }

    @Override
    public void updateUser(Long idUser, UserDto userDto) throws UserNotFoundException {
        // Implementation of updating a user
    }

    @Override
    public void deleteUser(Long idUser) throws UserNotFoundException {
        // Implementation of deleting a user
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
