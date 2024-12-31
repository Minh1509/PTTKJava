package org.example.projectfinal.services.impl;

import org.example.projectfinal.entity.User;
import org.example.projectfinal.repository.UserRepository;
import org.example.projectfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public String register(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null) return "User đã tồn tại";

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);

        userRepository.save(newUser);
        return "Đăng ký thành công";

    }

    @Override
    public boolean login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user == null || !password.equals(user.getPassword())) return false;
        return true;
    }


}
