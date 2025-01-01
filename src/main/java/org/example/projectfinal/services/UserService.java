package org.example.projectfinal.services;

import jakarta.servlet.http.HttpSession;
import org.example.projectfinal.entity.User;

public interface UserService {
    boolean register(String userName, String password, HttpSession httpSession);
    boolean changePassword (Long userId, String currentPassword, String newPassword, String confirmNewPassword);
    boolean login (String userName, String password, HttpSession session);
}
