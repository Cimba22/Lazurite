package com.cimba.lazurite;

import com.cimba.lazurite.entity.Role;
import com.cimba.lazurite.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class LazuriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LazuriteApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByRoleName("USER").isEmpty()) {
                roleRepository.save(Role.builder().roleName("USER").build());
            }
        };
    }


}
