package ru.inno.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.messenger.models.User;
import ru.inno.messenger.repositories.UsersRepository;
import ru.inno.messenger.security.details.CustomUserDetails;
import ru.inno.messenger.service.ProfileService;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final UsersRepository usersRepository;

    @Override
    public User getCurrent(CustomUserDetails userDetails) {
        return usersRepository.findById(userDetails.getUser().getId()).orElseThrow();
    }
}
