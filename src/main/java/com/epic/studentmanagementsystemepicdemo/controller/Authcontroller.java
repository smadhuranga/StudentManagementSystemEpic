/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.controller;

import com.epic.studentmanagementsystemepicdemo.model.User;
import com.epic.studentmanagementsystemepicdemo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/auth")
@CrossOrigin
public class Authcontroller {

    private final AuthService authService;

    public Authcontroller(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return authService.getUserByUsername(username);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        authService.registerUser(user);
        return "User Register successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        authService.login(user);
        return "User Login successfully";
    }


}
