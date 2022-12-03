package ru.inno.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inno.messenger.models.Message;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.MessagesService;

import javax.servlet.http.HttpServletRequest;

import static ru.inno.messenger.util.Methods.returnToPreviousPage;

@RequiredArgsConstructor
@Controller
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesService messagesService;

    @GetMapping
    public String getMessagesPage(Model model) {
        model.addAttribute("messages", messagesService.getAllMessagesOrderedByChat());
        model.addAttribute("chats", messagesService.getAllChats());
        return "messages/messages_page";
    }

    @PostMapping
    public String addMessage(HttpServletRequest request,
                             Message message,
                             @RequestParam("chat-id") Long chatId) {
        messagesService.addMessage(chatId, message);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{message-id}")
    public String getMessagePage(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @PathVariable("message-id") Long messageId,
                                 Model model) {
        model.addAttribute("message", messagesService.getMessage(messageId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "messages/message_page";
    }

    @GetMapping("/{message-id}/delete")
    public String deleteMessage(HttpServletRequest request,
                                @PathVariable("message-id") Long messageId) {
        messagesService.deleteMessage(messageId);
        return returnToPreviousPage(request);
    }

    @PostMapping("/{message-id}/update")
    public String updateMessage(HttpServletRequest request,
                                @PathVariable("message-id") Long messageId,
                                Message message) {
        messagesService.updateMessage(messageId, message);
        return returnToPreviousPage(request);
    }
}
