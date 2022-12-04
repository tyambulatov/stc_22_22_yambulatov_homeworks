package ru.inno.messenger.service;

import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.Message;

import java.util.List;

public interface MessagesService {
    void addMessageToChat(Long chatId, Message message);

    void deleteMessage(Long messageId);

    Message getMessage(Long messageId);

    void updateMessage(Long messageId, Message message);

    List<Chat> getAllChats();

    List<Message> getAllMessagesOrderedByChat();
}
