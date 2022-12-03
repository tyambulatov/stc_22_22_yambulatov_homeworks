package ru.inno.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inno.messenger.dto.UserForm;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static ru.inno.messenger.util.Methods.returnToPreviousPage;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users/users_page";
    }

    @PostMapping
    public String addUser(HttpServletRequest request,
                          UserForm user) {
        usersService.addUser(user);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{user-id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getUserPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable("user-id") Long userId,
                              Model model) {
        model.addAttribute("user", usersService.getUser(userId));
        model.addAttribute("userChats", usersService.getUserChats(userId));
        model.addAttribute("chatsWithoutUser", usersService.getChatsWithoutUser(userId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "users/user_page";
    }

    @PostMapping("/{user-id}/update")
    public String updateUser(HttpServletRequest request,
                             @PathVariable("user-id") Long userId,
                             UserForm user) {
        usersService.updateUser(userId, user);
        return returnToPreviousPage(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{user-id}/delete")
    public String deleteUser(HttpServletRequest request,
                             @PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return returnToPreviousPage(request);
    }

    @PostMapping("/{user-id}/addUserToChat")
    public String addUserToChat(HttpServletRequest request,
                                @PathVariable("user-id") Long userId,
                                @RequestParam("chat-id") Long chatId) {
        usersService.addUserToChat(userId, chatId);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{user-id}/deleteUserFromChat")
    public String deleteUserFromChat(HttpServletRequest request,
                                    @PathVariable("user-id") Long userId,
                                    @RequestParam("chat-id") Long chatId) {
        usersService.deleteUserFromChat(userId, chatId);
        return returnToPreviousPage(request);
    }
}
