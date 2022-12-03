package ru.inno.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.messenger.models.Chat;

public interface ChatsRepository extends JpaRepository<Chat, Long> {

}
