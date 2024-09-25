package com.backend.smartbill.dto;

import lombok.Data;

@Data
public class AuthRegisterRequestDto {
    private String user;
    private String phone;
    private String password;
}
