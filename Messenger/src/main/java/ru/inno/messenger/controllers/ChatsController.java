package ru.inno.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.ChatsService;
import ru.inno.messenger.service.MessagesService;

import javax.servlet.http.HttpServletRequest;

import static ru.inno.messenger.util.Methods.returnToPreviousPage;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chats")
public class ChatsController {

    private final ChatsService chatsService;

    private final MessagesService messagesService;

    @GetMapping
    public String getChatsPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                               Model model) {
        model.addAttribute("chats", chatsService.getAllChats());

        Long userId = userDetails.getUser().getId();
        model.addAttribute("userId", userId);
        model.addAttribute("userChats", chatsService.getUserChats(userId));
        model.addAttribute("chatsWithoutUser", chatsService.getChatsWithoutUser(userId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "chats/chats_page";
    }

    @PostMapping
    public String addChat(Chat chat,
                          HttpServletRequest request) {
        chatsService.addChat(chat);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{chatId}")
    public String getChatPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable("chatId") Long chatId,
                              Model model) {
        model.addAttribute("chat", chatsService.getChat(chatId));
        model.addAttribute("notInChatUsers", chatsService.getNotInChatUsers(chatId));
        model.addAttribute("inChatUsers", chatsService.getInChatUsers(chatId));
        model.addAttribute("chatMessages", messagesService.getChatMessages(chatId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "chats/chat_page";
    }

    @PostMapping("/{chatId}/update")
    public String updateChat(@PathVariable("chatId") Long chatId,
                             Chat chat,
                             HttpServletRequest request) {
        chatsService.updateChat(chatId, chat);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{chatId}/delete")
    public String deleteChat(@PathVariable("chatId") Long chatId,
                             HttpServletRequest request) {
        chatsService.deleteChat(chatId);
        return returnToPreviousPage(request);
    }

    @PostMapping("/{chatId}/users")
    public String addUserToChat(@PathVariable("chatId") Long chatId,
                                @RequestParam("user-id") Long userId,
                                HttpServletRequest request) {
        chatsService.addUserToChat(chatId, userId);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{chatId}/users/{userId}/delete")
    public String deleteUserFromChat(@PathVariable("chatId") Long chatId,
                                     @PathVariable("userId") Long userId,
                                     HttpServletRequest request) {
        chatsService.deleteUserFromChat(chatId, userId);
        return returnToPreviousPage(request);
    }
}
