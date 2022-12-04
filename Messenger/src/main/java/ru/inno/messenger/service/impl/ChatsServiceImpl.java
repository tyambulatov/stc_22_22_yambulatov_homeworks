package ru.inno.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.Message;
import ru.inno.messenger.models.User;
import ru.inno.messenger.repositories.ChatsRepository;
import ru.inno.messenger.repositories.MessagesRepository;
import ru.inno.messenger.repositories.UsersRepository;
import ru.inno.messenger.service.ChatsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ChatsServiceImpl implements ChatsService {

    private final ChatsRepository chatsRepository;
    private final UsersRepository usersRepository;

    private final MessagesRepository messagesRepository;

    @Override
    public Chat getChat(Long chatId) {
        return chatsRepository.findById(chatId).orElseThrow();
    }

    @Override
    public List<User> getNotInChatUsers(Long chatId) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();
        return usersRepository.findAllByChatsNotContains(chat);
    }

    @Override
    public List<User> getInChatUsers(Long chatId) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();
        return usersRepository.findAllByChatsContains(chat);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatsRepository.findAll();
    }

    @Override
    public void addChat(Chat chat) {
        chatsRepository.save(chat);
    }

    @Override
    public void updateChat(Long chatId, Chat chat) {
        Chat chatForUpdate = chatsRepository.findById(chatId).orElseThrow();
        chatForUpdate.setName(chat.getName());
        chatsRepository.save(chatForUpdate);
    }

    @Override
    public List<Message> getChatMessages(Long chatId) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();
        List<Message> chatMessages = new ArrayList<>(chat.getMessages());
        chatMessages.sort(Comparator.comparing(Message::getSendTime));
        return chatMessages;
    }

    @Override
    public void deleteChat(Long chatId) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();

        Set<User> usersInChat = chat.getUsers();
        usersInChat.forEach(user -> {
            user.getChats().remove(chat);
            usersRepository.save(user);
        });

        messagesRepository.deleteAll(chat.getMessages());
        chatsRepository.delete(chat);
    }
}
