package org.example.projectfinal.services;

import jakarta.servlet.http.HttpSession;
import org.example.projectfinal.entity.User;

public interface UserService {
    String register(String userName, String password, HttpSession httpSession);

    boolean login (String userName, String password, HttpSession session);
}
