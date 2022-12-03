package ru.inno.messenger.service;

import ru.inno.messenger.dto.UserForm;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    void addUser(UserForm user);

    User getUser(Long id);

    void updateUser(Long userId, UserForm updateData);

    void deleteUser(Long userId);

    List<Chat> getUserChats(Long userId);

    void deleteUserFromChat(Long userId, Long chatId);

    void addUserToChat(Long userId, Long chatId);

    List<Chat> getChatsWithoutUser(Long userId);
}
