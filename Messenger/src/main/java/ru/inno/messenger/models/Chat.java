package ru.inno.messenger.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    private Set<Message> messages;

    @ManyToMany(mappedBy = "chats", fetch = FetchType.EAGER)
    private Set<User> users;
}
