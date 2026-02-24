/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.model.User;
import com.epic.studentmanagementsystemepicdemo.repository.impl.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(User user) {
        userRepository.saveUser(user);
    }

    public String login(User user) {
        User dbUser = userRepository.findByUsername(user.getName());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            return "User Login successfully";
        }
        throw new UsernameNotFoundException("Username or password is incorrect");

    }


}
