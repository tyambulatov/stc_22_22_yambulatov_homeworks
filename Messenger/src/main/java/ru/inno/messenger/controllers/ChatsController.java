package ru.inno.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.Message;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.ChatsService;
import ru.inno.messenger.service.MessagesService;
import ru.inno.messenger.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static ru.inno.messenger.util.Methods.returnToPreviousPage;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chats")
public class ChatsController {

    private final ChatsService chatsService;

    private final UsersService usersService;

    private final MessagesService messagesService;

    @GetMapping
    public String getChatsPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("role", userDetails.getUser().getRole());
        model.addAttribute("chats", chatsService.getAllChats());

        Long userId = userDetails.getUser().getId();
        model.addAttribute("userId", userId);
        model.addAttribute("userChats", usersService.getUserChats(userId));
        model.addAttribute("chatsWithoutUser", usersService.getChatsWithoutUser(userId));
        return "chats/chats_page";
    }

    @PostMapping
    public String addChat(HttpServletRequest request,
                          Chat chat) {
        chatsService.addChat(chat);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{chat-id}")
    public String getChatPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @PathVariable("chat-id") Long chatId,
                              Model model) {
        model.addAttribute("chat", chatsService.getChat(chatId));
        model.addAttribute("notInChatUsers", chatsService.getNotInChatUsers(chatId));
        model.addAttribute("inChatUsers", chatsService.getInChatUsers(chatId));
        model.addAttribute("chatMessages", chatsService.getChatMessages(chatId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "chats/chat_page";
    }

    @GetMapping("/{chat-id}/deleteUserFromChat")
    public String deleteUserFromChat(HttpServletRequest request,
                                     @PathVariable("chat-id") Long chatId,
                                     @RequestParam("user-id") Long userId) {
        usersService.deleteUserFromChat(userId, chatId);
        return returnToPreviousPage(request);
    }

    @PostMapping("/{chat-id}/addUserToChat")
    public String addUserToChat(HttpServletRequest request,
                                @PathVariable("chat-id") Long chatId,
                                @RequestParam("user-id") Long userId) {
        usersService.addUserToChat(userId, chatId);
        return returnToPreviousPage(request);
    }

    @PostMapping("/{chat-id}/messages")
    public String addMessageToChat(HttpServletRequest request,
                                   @PathVariable("chat-id") Long chatId,
                                   Message message) {
        messagesService.addMessageToChat(chatId, message);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{chat-id}/delete")
    public String deleteChat(HttpServletRequest request,
                             @PathVariable("chat-id") Long chatId) {
        chatsService.deleteChat(chatId);
        return returnToPreviousPage(request);
    }

    @PostMapping("/{chat-id}/update")
    public String updateChat(HttpServletRequest request,
                             @PathVariable("chat-id") Long chatId,
                             Chat chat) {
        chatsService.updateChat(chatId, chat);
        return returnToPreviousPage(request);
    }
}
