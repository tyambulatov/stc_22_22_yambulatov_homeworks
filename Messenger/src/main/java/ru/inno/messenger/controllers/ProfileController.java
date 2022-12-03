package ru.inno.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.ProfileService;

@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/")
    public String getRoot() {
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                             Model model) {
        model.addAttribute("user", profileService.getCurrent(userDetails));
        model.addAttribute("role", userDetails.getUser().getRole());
        return "profile_page";
    }
}
