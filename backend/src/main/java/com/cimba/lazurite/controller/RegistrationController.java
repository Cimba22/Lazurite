package com.cimba.lazurite.controller;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.UserRole;
import com.cimba.lazurite.repository.UserRepository;
import com.cimba.lazurite.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @PostMapping("/register/user")
    public User createUser(@RequestBody User user){
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        // Сохраняем пользователя в базе данных
        User savedUser = userRepository.save(user);

//        // Создаем запись в таблице user_role для нового пользователя
//        UserRole userRole = new UserRole();
//        userRole.setUserId(savedUser.getIdUser()); // Устанавливаем идентификатор пользователя
//        userRole.setRoleId(2L);                // Устанавливаем идентификатор роли 2
//        userRoleRepository.save(userRole);     // Сохраняем запись в таблице user_role

        UserRole userRole = UserRole.builder()
                .userId(savedUser.getIdUser())
                .roleId(2L)
                .build();
        userRoleRepository.save(userRole);

        return savedUser;
    }
}
