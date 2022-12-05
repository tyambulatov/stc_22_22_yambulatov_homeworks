package ru.inno.messenger.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.User;
import ru.inno.messenger.repositories.ChatsRepository;
import ru.inno.messenger.repositories.MessagesRepository;
import ru.inno.messenger.repositories.UsersRepository;
import ru.inno.messenger.service.ChatsService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChatsServiceImplTest {

    private static final long EXISTED_CHAT_ID = 1L;

    private static final long USER_ID = 4L;

    private static final long NOT_EXISTED_CHAT_ID = 2L;

    private static final List<User> USERS_OF_EXISTED_CHAT = List.of(
            User.builder().id(1L).email("test@1.com").build(),
            User.builder().id(2L).email("test@2.com").build(),
            User.builder().id(3L).email("test@3.com").build()
    );

    private static final User USER = User.builder()
            .id(USER_ID)
            .email("test@4.com")
            .chats(new HashSet<>())
            .build();

    private static final Chat EXISTED_CHAT = Chat.builder()
            .id(EXISTED_CHAT_ID)
            .name("Тестовый чат")
            .build();

    private ChatsService chatsService;

    private ChatsRepository chatsRepository;

    private UsersRepository usersRepository;

    private MessagesRepository messagesRepository;

    @BeforeEach
    void setUp() {
        setUpMocks();
        stubMocks();
        this.chatsService = new ChatsServiceImpl(chatsRepository, usersRepository, messagesRepository);

    }

    private void setUpMocks() {
        this.chatsRepository = Mockito.mock(ChatsRepository.class);
        this.usersRepository = Mockito.mock(UsersRepository.class);
        this.messagesRepository = Mockito.mock(MessagesRepository.class);
    }

    private void stubMocks() {
        when(chatsRepository.findById(EXISTED_CHAT_ID)).thenReturn(Optional.of(EXISTED_CHAT));
        when(chatsRepository.findById(NOT_EXISTED_CHAT_ID)).thenReturn(Optional.empty());

        when(usersRepository.findAllByChatsContains(EXISTED_CHAT)).thenReturn(USERS_OF_EXISTED_CHAT);
        when(usersRepository.findById(USER_ID)).thenReturn(Optional.of(USER));
    }

    @Test
    void get_in_chat_users_for_existed_chat() {
        List<User> actual = chatsService.getInChatUsers(EXISTED_CHAT_ID);
        List<User> expected = USERS_OF_EXISTED_CHAT;
        assertEquals(expected, actual);
    }

    @Test
    void get_in_chat_users_for_not_existed_chat() {
        assertThrows(RuntimeException.class, () ->
            chatsService.getInChatUsers(NOT_EXISTED_CHAT_ID)
        );
    }

    @Test
    void add_user_to_chat() {
        chatsService.addUserToChat(EXISTED_CHAT_ID, USER_ID);
        verify(usersRepository).save(USER);
    }
}