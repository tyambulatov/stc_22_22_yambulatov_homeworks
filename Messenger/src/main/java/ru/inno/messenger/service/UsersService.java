package ru.inno.messenger.service;

import ru.inno.messenger.dto.UserForm;
import ru.inno.messenger.models.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    void addUser(UserForm user);

    User getUser(Long id);

    void updateUser(Long userId, UserForm updateData);

    void deleteUser(Long userId);
}
