package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.Role;
import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.UserRole;
import com.cimba.lazurite.entity.dto.UserDto;
import com.cimba.lazurite.exception.RegistrationException;
import com.cimba.lazurite.exception.UserNotFoundException;
import com.cimba.lazurite.repository.RoleRepository;
import com.cimba.lazurite.repository.UserRepository;
import com.cimba.lazurite.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, RoleRepository roleRepository, UserRoleRepository userRoleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Role getUserRole(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            var userObj = user.get();
            return (Role) userObj.getRoleSet();
        }else {
            throw new UsernameNotFoundException(email);
        }
    }

    @Override
    public void registerUser(UserDto userDto) throws RegistrationException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RegistrationException("User with email " + userDto.getEmail() + " already exists");
        }

        User user = User.builder()
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .passwordHash(passwordEncoder.encode(userDto.getPasswordHash()))
                .build();



        userRepository.save(user);
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
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User with id " + idUser + " not found"));

        existingUser.setLogin(userDto.getLogin());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPasswordHash(userDto.getPasswordHash());

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long idUser) throws UserNotFoundException {
        if (!userRepository.existsById(idUser)) {
            throw new UserNotFoundException("User with id " + idUser + " not found");
        }

        userRepository.deleteById(idUser);
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .idUser(user.getIdUser())
                .login(user.getLogin())
                .email(user.getEmail())
                .registrationDate(user.getRegistrationDate())
                .build();
    }
}
