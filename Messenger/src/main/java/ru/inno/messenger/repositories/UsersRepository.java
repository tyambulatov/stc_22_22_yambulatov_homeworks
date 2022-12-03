package ru.inno.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.messenger.models.Chat;
import ru.inno.messenger.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAllByChatsNotContains(Chat chat);

    List<User> findAllByChatsContains(Chat chat);

    Optional<User> findByEmail(String email);
}
