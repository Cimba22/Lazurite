package com.cimba.lazurite.entity.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long idUser;
    private String login;
    private String email;
    private String passwordHash;
    private Date registrationDate;

}
