package ru.inno.messenger.service;


import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.User;

import java.util.List;

public interface ChatsService {

    List<Chat> getAllChats();

    List<Chat> getUserChats(Long userId);

    List<Chat> getChatsWithoutUser(Long userId);

    void addChat(Chat chat);

    Chat getChat(Long chatId);

    List<User> getNotInChatUsers(Long chatId);

    List<User> getInChatUsers(Long chatId);

    void updateChat(Long chatId, Chat chat);

    void deleteChat(Long chatId);

    void addUserToChat(Long chatId, Long userId);

    void deleteUserFromChat(Long chatId, Long userId);
}
