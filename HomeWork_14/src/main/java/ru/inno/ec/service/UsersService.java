package ru.inno.ec.service;

import ru.inno.ec.dto.UserForm;
import ru.inno.ec.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    void addUser(UserForm user);

    User getUser(Long id);

    void updateUser(Long userId, UserForm updateData);

    void deleteUser(Long userId);
}
