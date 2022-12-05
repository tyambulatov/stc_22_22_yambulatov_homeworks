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
        model.addAttribute("messages", messagesService.getAllMessages());
        model.addAttribute("chats", messagesService.getAllChats());
        return "messages/messages_page";
    }

    @PostMapping
    public String addMessage(@RequestParam("chat-id") Long chatId,
                             Message message,
                             HttpServletRequest request) {
        messagesService.addMessageToChat(chatId, message);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{messageId}")
    public String getMessagePage(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @PathVariable("messageId") Long messageId,
                                 Model model) {
        model.addAttribute("message", messagesService.getMessage(messageId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "messages/message_page";
    }

    @PostMapping("/{messageId}/update")
    public String updateMessage(@PathVariable("messageId") Long messageId,
                                Message message,
                                HttpServletRequest request) {
        messagesService.updateMessage(messageId, message);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{messageId}/delete")
    public String deleteMessage(@PathVariable("messageId") Long messageId,
                                HttpServletRequest request) {
        messagesService.deleteMessage(messageId);
        return returnToPreviousPage(request);
    }
}
