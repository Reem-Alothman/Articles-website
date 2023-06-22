package com.example.demo;

import com.example.demo.models.Privilege;
import com.example.demo.models.PrivilegeName;
import com.example.demo.models.User;
import com.example.demo.repositories.PrivilegeRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public void run(String...args) throws Exception {
        User admin = new User();

        admin.setUsername("admin");
        admin.setEmail("admin@test.com");
        admin.setMobileNumber(1987654321);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode("adminPassword"));

        List<Privilege> prevs = new ArrayList<>();

        Privilege p1 = new Privilege();
        p1.setName(PrivilegeName.ADMIN);
        prevs.add(p1);

        Privilege p2 = new Privilege();
        p2.setName(PrivilegeName.USER);
        prevs.add(p2);

        admin.setPrivileges(prevs);
        userRepository.save(admin);

        System.out.println(admin.toString());
        System.out.println("Admin user has created");
    }


}