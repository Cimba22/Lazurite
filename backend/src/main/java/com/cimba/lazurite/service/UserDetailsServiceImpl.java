package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.Role;
import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    Optional<User> user = repository.findByEmail(email);
//        if(user.isPresent()){
//        var userObj = user.get();
//        Set<String> roles = userObj.getRoleSet().stream()
//                .map(Role::getRoleName)
//                .collect(Collectors.toSet());
//        String[] rolesArray = roles.toArray(new String[0]);
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(userObj.getEmail())
//                .password(userObj.getPasswordHash())
//                .roles(rolesArray)
//                .build();
//    }else {
//        throw new UsernameNotFoundException(email);
//    }
}
