package ru.inno.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.User;
import ru.inno.messenger.repositories.ChatsRepository;
import ru.inno.messenger.repositories.MessagesRepository;
import ru.inno.messenger.repositories.UsersRepository;
import ru.inno.messenger.service.ChatsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ChatsServiceImpl implements ChatsService {

    private final ChatsRepository chatsRepository;

    private final UsersRepository usersRepository;

    private final MessagesRepository messagesRepository;

    @Override
    public List<Chat> getAllChats() {
        return chatsRepository.findAll();
    }

    @Override
    public List<Chat> getUserChats(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow();
        return new ArrayList<>(user.getChats());
    }

    @Override
    public List<Chat> getChatsWithoutUser(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow();
        List<Chat> allChats = chatsRepository.findAll();
        allChats.removeAll(user.getChats());
        return allChats;
    }

    @Override
    public void addChat(Chat chat) {
        chatsRepository.save(chat);
    }

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
    public void updateChat(Long chatId, Chat chat) {
        Chat chatForUpdate = chatsRepository.findById(chatId).orElseThrow();
        chatForUpdate.setName(chat.getName());
        chatsRepository.save(chatForUpdate);
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

    @Override
    public void addUserToChat(Long chatId, Long userId) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();
        User user = usersRepository.findById(userId).orElseThrow();

        user.getChats().add(chat);
        usersRepository.save(user);
    }

    @Override
    public void deleteUserFromChat(Long chatId, Long userId) {
        Chat chat = chatsRepository.findById(chatId).orElseThrow();
        User user = usersRepository.findById(userId).orElseThrow();
        user.getChats().remove(chat);
        chat.getUsers().remove(user);
        chatsRepository.save(chat);
        usersRepository.save(user);
    }
}
