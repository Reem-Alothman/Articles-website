package com.example.demo.mapstruct.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotEmpty(message ="username cannot be empty")
    private String username;
    @NotNull(message = "mobile number cannot be empty")
    private int mobileNumber;
    @NotEmpty(message = "you have to enter a password")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    private String email;

    public UserDto() {

    }

    public UserDto(String username, String email, String password, int mobileNumber) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

}
