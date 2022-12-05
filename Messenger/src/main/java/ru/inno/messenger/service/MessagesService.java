package ru.inno.messenger.service;

import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.Message;

import java.util.List;

public interface MessagesService {
    List<Message> getAllMessages();

    List<Chat> getAllChats();

    void addMessageToChat(Long chatId, Message message);

    Message getMessage(Long messageId);

    void updateMessage(Long messageId, Message message);

    void deleteMessage(Long messageId);

    List<Message> getChatMessages(Long chatId);
}
