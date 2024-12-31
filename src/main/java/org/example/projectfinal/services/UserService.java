package org.example.projectfinal.services;

import org.example.projectfinal.entity.User;

public interface UserService {
    String register(String userName, String password);

    boolean login (String userName, String password);
}
