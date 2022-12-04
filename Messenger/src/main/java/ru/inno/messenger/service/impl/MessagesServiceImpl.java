package ru.inno.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.Message;
import ru.inno.messenger.repositories.ChatsRepository;
import ru.inno.messenger.repositories.MessagesRepository;
import ru.inno.messenger.service.MessagesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;

    private final ChatsRepository chatsRepository;

    @Override
    public void addMessageToChat(Long chatId, Message message) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();
        chat.getMessages().add(message);
        message.setChat(chat);

        chatsRepository.save(chat);
        messagesRepository.save(message);
    }

    @Override
    public void deleteMessage(Long messageId) {
        Message message = messagesRepository.findById(messageId).orElseThrow();
        Chat chat = message.getChat();
        chat.getMessages().remove(message);
        chatsRepository.save(chat);
        messagesRepository.delete(message);
    }

    @Override
    public Message getMessage(Long messageId) {
        return messagesRepository.findById(messageId).orElseThrow();
    }

    @Override
    public void updateMessage(Long messageId, Message message) {
        Message messageForUpdate = messagesRepository.findById(messageId).orElseThrow();
        messageForUpdate.setContent(message.getContent());
        messageForUpdate.setSendTime(message.getSendTime());
        messagesRepository.save(messageForUpdate);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatsRepository.findAll();
    }

    @Override
    public List<Message> getAllMessagesOrderedByChat() {
        return messagesRepository.findByOrderByChatAscSendTimeAsc();
    }
}
