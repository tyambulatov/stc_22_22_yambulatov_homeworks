package ru.inno.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.inno.messenger.dto.UserForm;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.ChatsService;
import ru.inno.messenger.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static ru.inno.messenger.util.Methods.returnToPreviousPage;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    private final ChatsService chatsService;

    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users/users_page";
    }

    @PostMapping
    public String addUser(UserForm user,
                          HttpServletRequest request) {
        usersService.addUser(user);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{userId}")
    public String getUserPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable("userId") Long userId,
                              Model model) {
        model.addAttribute("user", usersService.getUser(userId));
        model.addAttribute("userChats", chatsService.getUserChats(userId));
        model.addAttribute("chatsWithoutUser", chatsService.getChatsWithoutUser(userId));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "users/user_page";
    }

    @PostMapping("/{userId}/update")
    public String updateUser(@PathVariable("userId") Long userId,
                             UserForm user,
                             HttpServletRequest request) {
        usersService.updateUser(userId, user);
        return returnToPreviousPage(request);
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long userId,
                             HttpServletRequest request) {
        usersService.deleteUser(userId);
        return returnToPreviousPage(request);
    }
}
