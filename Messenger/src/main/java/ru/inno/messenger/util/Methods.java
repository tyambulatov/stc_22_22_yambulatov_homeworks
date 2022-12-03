package ru.inno.messenger.util;

import javax.servlet.http.HttpServletRequest;

public class Methods {
    public static String returnToPreviousPage(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
