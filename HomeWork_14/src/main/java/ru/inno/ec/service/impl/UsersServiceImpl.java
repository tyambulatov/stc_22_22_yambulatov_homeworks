package ru.inno.ec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.ec.dto.UserForm;
import ru.inno.ec.models.User;
import ru.inno.ec.repositories.UsersRepository;
import ru.inno.ec.service.UsersService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAllByStateNot(User.State.DELETED);
    }

    @Override
    public void addUser(UserForm user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .state(User.State.NOT_CONFIRMED)
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
        userForUpdate.setAge(updateData.getAge());

        usersRepository.save(userForUpdate);
    }

    @Override
    public void deleteUser(Long userId) {
        User userForDelete = usersRepository.findById(userId).orElseThrow();
        userForDelete.setState(User.State.DELETED);

        usersRepository.save(userForDelete);
    }
}
