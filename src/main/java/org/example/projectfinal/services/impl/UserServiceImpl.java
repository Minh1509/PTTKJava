package org.example.projectfinal.services.impl;

import jakarta.servlet.http.HttpSession;
import org.example.projectfinal.entity.User;
import org.example.projectfinal.repository.UserRepository;
import org.example.projectfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;


    @Override
    public boolean register(String userName, String password, HttpSession session) {
        User user = userRepository.findByUserName(userName);
        if (user != null) return false;

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        session.setAttribute("user", newUser);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public boolean changePassword(Long userId, String currentPassword, String newPassword, String confirmNewPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(currentPassword.equals(newPassword) || currentPassword.equals(confirmNewPassword)) return false;
        if(!newPassword.equals(confirmNewPassword)) return false;

        user.setPassword(currentPassword);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(String userName, String password, HttpSession session) {
        User user = userRepository.findByUserName(userName);
        if (user == null || !password.equals(user.getPassword())) return false;
        httpSession.setAttribute("user", user);
        return true;
    }


}
