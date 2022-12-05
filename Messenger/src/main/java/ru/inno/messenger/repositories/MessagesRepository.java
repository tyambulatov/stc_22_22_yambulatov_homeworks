package ru.inno.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.messenger.models.Message;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, Long> {
    List<Message> findByOrderByChatAscSendTimeAsc();
}
