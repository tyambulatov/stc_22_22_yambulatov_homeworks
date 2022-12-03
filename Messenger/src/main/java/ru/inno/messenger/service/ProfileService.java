package ru.inno.messenger.service;

import ru.inno.messenger.models.User;
import ru.inno.messenger.security.details.CustomUserDetails;

public interface ProfileService {
    User getCurrent(CustomUserDetails userDetails);
}
