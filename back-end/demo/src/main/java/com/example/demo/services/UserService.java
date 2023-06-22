package com.example.demo.services;

import com.example.demo.mapstruct.dtos.UserDto;
import com.example.demo.mapstruct.mapper.MapStructMapper;
import com.example.demo.mapstruct.mapper.MapStructMapperImpl;
import com.example.demo.models.Privilege;
import com.example.demo.models.PrivilegeName;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.repositories.PrivilegeRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired ArticleRepository articleRepository;
    @Autowired PrivilegeRepository privilegeRepository;
    @Autowired MapStructMapperImpl mapStructMapper;


    public void createUser(UserDto userDto){

        User newUser =  mapStructMapper.userDtoToUser(userDto);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        List<Privilege> prevs = new ArrayList<>();
        prevs.add(privilegeRepository.findPrivilegeByName(PrivilegeName.USER));
        newUser.setPrivileges(prevs);

        userRepository.save(newUser);
    }

    public void logoutUser(){
        SecurityContextHolder.clearContext();
    }






}
