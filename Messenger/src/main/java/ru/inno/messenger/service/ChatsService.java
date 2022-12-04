package ru.inno.messenger.service;


import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.Message;
import ru.inno.messenger.models.User;

import java.util.List;

public interface ChatsService {

    Chat getChat(Long chatId);

    List<User> getNotInChatUsers(Long chatId);

    List<User> getInChatUsers(Long chatId);

    List<Chat> getAllChats();

    void deleteChat(Long chatId);

    void addChat(Chat chat);

    void updateChat(Long chatId, Chat chat);

    List<Message> getChatMessages(Long chatId);

}
