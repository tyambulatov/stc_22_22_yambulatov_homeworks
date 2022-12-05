package ru.inno.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inno.messenger.dto.UserForm;
import ru.inno.messenger.models.User;
import ru.inno.messenger.repositories.UsersRepository;
import ru.inno.messenger.service.UsersService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(UserForm user) {
        String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordHash)
                .role(User.Role.USER)
                .build();
        usersRepository.save(newUser);
    }

    @Override
    public User getUser(Long id) {
        return usersRepository.findById(id).orElseThrow();
    }

    @Override
    public void updateUser(Long userId, UserForm updateData) {
        User userForUpdate = usersRepository.findById(userId).orElseThrow();

        userForUpdate.setFirstName(updateData.getFirstName());
        userForUpdate.setLastName(updateData.getLastName());

        usersRepository.save(userForUpdate);
    }

    @Override
    public void deleteUser(Long userId) {
        User userForDelete = usersRepository.findById(userId).orElseThrow();
        userForDelete.getChats().clear();

        usersRepository.save(userForDelete);
        usersRepository.delete(userForDelete);
    }
}
