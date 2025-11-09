package com.jazzy.journalApp;

import com.jazzy.journalApp.entity.User;
import com.jazzy.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/public")
public class Public {

    @Autowired
    private UserService userService;


@GetMapping("health-check")
    public String healthCheck() {
    return "OK";
}

@PostMapping("/create-user")
public void createUser(@RequestBody User user)
    {
    user.setRoles(Arrays.asList("USER"));
    userService.saveUserEntry(user);
    }
}


